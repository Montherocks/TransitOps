package com.TransitOps.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuelLogResponseDTO {

    private Long id;

    private Long vehicleId;

    private String registrationNumber;

    private Long tripId;

    private LocalDate fuelDate;

    private Double liters;

    private BigDecimal cost;

    private String fuelStation;

}