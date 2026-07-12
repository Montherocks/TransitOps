package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.ExpenseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateExpenseRequestDTO {

    @NotNull
    private ExpenseType expenseType;

    @Positive
    private BigDecimal amount;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate expenseDate;

}