package com.TransitOps.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuelStatisticsDTO {

    private Long totalLogs;

    private Double totalFuelConsumed;

    private BigDecimal totalFuelCost;

    private Double averageFuelPerLog;

}