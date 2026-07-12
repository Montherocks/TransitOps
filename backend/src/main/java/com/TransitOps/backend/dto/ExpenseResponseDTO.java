package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.ExpenseType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDTO {

    private Long id;

    private Long vehicleId;

    private String registrationNumber;

    private Long tripId;

    private ExpenseType expenseType;

    private BigDecimal amount;

    private String description;

    private LocalDate expenseDate;

}