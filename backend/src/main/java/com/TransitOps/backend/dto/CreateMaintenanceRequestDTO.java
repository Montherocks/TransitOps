package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.MaintenanceType;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMaintenanceRequestDTO {

    @NotNull
    private Long vehicleId;

    @NotNull
    private MaintenanceType maintenanceType;

    @NotBlank
    private String description;

    @NotBlank
    private String serviceCenter;

    @Positive
    private BigDecimal cost;

    @NotNull
    private LocalDate scheduledDate;

}