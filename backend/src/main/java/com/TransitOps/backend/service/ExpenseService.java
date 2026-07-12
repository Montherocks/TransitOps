package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.CreateExpenseRequestDTO;
import com.TransitOps.backend.dto.ExpenseResponseDTO;
import com.TransitOps.backend.dto.ExpenseStatisticsDTO;
import com.TransitOps.backend.dto.UpdateExpenseRequestDTO;
import org.springframework.data.domain.Page;

public interface ExpenseService {

    ExpenseResponseDTO createExpense(CreateExpenseRequestDTO request);

    ExpenseResponseDTO getExpenseById(Long id);

    Page<ExpenseResponseDTO> getAllExpenses(int page, int size, String sortBy, String direction);

    ExpenseResponseDTO updateExpense(Long id, UpdateExpenseRequestDTO request);

    void deleteExpense(Long id);

    ExpenseStatisticsDTO getStatistics();

    Page<ExpenseResponseDTO> getExpensesByVehicle(Long vehicleId, int page, int size);

}
