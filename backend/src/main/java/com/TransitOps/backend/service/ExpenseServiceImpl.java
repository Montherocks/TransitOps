package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.CreateExpenseRequestDTO;
import com.TransitOps.backend.dto.ExpenseResponseDTO;
import com.TransitOps.backend.dto.ExpenseStatisticsDTO;
import com.TransitOps.backend.dto.UpdateExpenseRequestDTO;
import com.TransitOps.backend.entity.Expense;
import com.TransitOps.backend.entity.Trip;
import com.TransitOps.backend.entity.Vehicle;
import com.TransitOps.backend.exception.ExpenseNotFoundException;
import com.TransitOps.backend.mapper.ExpenseMapper;
import com.TransitOps.backend.repository.ExpenseRepository;
import com.TransitOps.backend.repository.TripRepository;
import com.TransitOps.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final VehicleRepository vehicleRepository;
    private final TripRepository tripRepository;

    @Override
    public ExpenseResponseDTO createExpense(CreateExpenseRequestDTO request){

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Trip trip = null;

        if(request.getTripId()!=null){

            trip = tripRepository.findById(request.getTripId())
                    .orElseThrow(() -> new RuntimeException("Trip not found"));

        }

        Expense expense = Expense.builder()
                .vehicle(vehicle)
                .trip(trip)
                .expenseType(request.getExpenseType())
                .amount(request.getAmount())
                .description(request.getDescription())
                .expenseDate(request.getExpenseDate())
                .build();

        Expense saved = expenseRepository.save(expense);

        return ExpenseMapper.toDTO(saved);

    }
    @Override
    @Transactional(readOnly = true)
    public ExpenseResponseDTO getExpenseById(Long id){

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found."));

        return ExpenseMapper.toDTO(expense);

    }
    @Override
    @Transactional(readOnly = true)
    public Page<ExpenseResponseDTO> getAllExpenses(
            int page,
            int size,
            String sortBy,
            String direction
    ){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        return expenseRepository.findAll(pageable).map(ExpenseMapper::toDTO);

    }
    @Override
    public ExpenseResponseDTO updateExpense(
            Long id,
            UpdateExpenseRequestDTO request

    ){

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found."));

        expense.setExpenseType(request.getExpenseType());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());

        Expense updated = expenseRepository.save(expense);

        return ExpenseMapper.toDTO(updated);

    }
    @Override
    public void deleteExpense(Long id){

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found."));

        expenseRepository.delete(expense);

    }
    @Override
    @Transactional(readOnly = true)
    public ExpenseStatisticsDTO getStatistics(){

        Long total = expenseRepository.getTotalExpenses();

        BigDecimal amount = expenseRepository.getTotalExpenseAmount();

        BigDecimal average = total == 0
                ? BigDecimal.ZERO
                : amount.divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP);

        return ExpenseStatisticsDTO.builder()
                .totalExpenses(total)
                .totalExpenseAmount(amount)
                .averageExpense(average)
                .build();

    }
    @Override
    @Transactional(readOnly = true)
    public Page<ExpenseResponseDTO> getExpensesByVehicle(Long vehicleId, int page, int size){

        Pageable pageable = PageRequest.of(page,size);

        return expenseRepository.findByVehicleId(vehicleId,pageable).map(ExpenseMapper::toDTO);

    }
}