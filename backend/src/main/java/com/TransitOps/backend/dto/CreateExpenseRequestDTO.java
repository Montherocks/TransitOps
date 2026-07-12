package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.ExpenseType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExpenseRequestDTO {

    @NotNull
    private Long vehicleId;

    private Long tripId;

    @NotNull
    private ExpenseType expenseType;

    @Positive
    private BigDecimal amount;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate expenseDate;

}