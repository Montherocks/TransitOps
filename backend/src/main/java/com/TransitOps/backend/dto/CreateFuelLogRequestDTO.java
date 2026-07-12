package com.TransitOps.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFuelLogRequestDTO {

    @NotNull
    private Long vehicleId;

    private Long tripId;

    @NotNull
    private LocalDate fuelDate;

    @Positive
    private Double liters;

    @Positive
    private BigDecimal cost;

    @NotBlank
    private String fuelStation;

}