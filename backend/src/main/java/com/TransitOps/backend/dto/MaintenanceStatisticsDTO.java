package com.TransitOps.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceStatisticsDTO {

    private Long totalMaintenance;

    private Long openMaintenance;

    private Long completedMaintenance;

    private BigDecimal totalMaintenanceCost;

}