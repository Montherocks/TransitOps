package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.MaintenanceStatus;
import com.TransitOps.backend.entity.MaintenanceType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceResponseDTO {

    private Long id;

    private Long vehicleId;

    private String vehicleRegistrationNumber;

    private MaintenanceType maintenanceType;

    private String description;

    private String serviceCenter;

    private BigDecimal cost;

    private LocalDate scheduledDate;

    private LocalDate completedDate;

    private MaintenanceStatus status;

}