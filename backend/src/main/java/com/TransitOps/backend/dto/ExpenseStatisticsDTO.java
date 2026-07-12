package com.TransitOps.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseStatisticsDTO {

    private Long totalExpenses;

    private BigDecimal totalExpenseAmount;

    private BigDecimal averageExpense;

}