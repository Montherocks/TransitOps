package com.TransitOps.backend.mapper;

import com.TransitOps.backend.dto.ExpenseResponseDTO;
import com.TransitOps.backend.entity.Expense;

public class ExpenseMapper {

    private ExpenseMapper(){}

    public static ExpenseResponseDTO toDTO(Expense expense){

        return ExpenseResponseDTO.builder()
                .id(expense.getId())
                .vehicleId(expense.getVehicle().getId())
                .registrationNumber(expense.getVehicle().getRegistrationNumber())
                .tripId(expense.getTrip()==null ? null : expense.getTrip().getId())
                .expenseType(expense.getExpenseType())
                .amount(expense.getAmount())
                .description(expense.getDescription())
                .expenseDate(expense.getExpenseDate())
                .build();
    }

}