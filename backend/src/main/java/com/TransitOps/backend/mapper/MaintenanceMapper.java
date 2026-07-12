package com.TransitOps.backend.mapper;

import com.TransitOps.backend.dto.MaintenanceResponseDTO;
import com.TransitOps.backend.entity.Maintenance;

public class MaintenanceMapper {

    private MaintenanceMapper() {
    }

    public static MaintenanceResponseDTO toDTO(Maintenance maintenance) {

        return MaintenanceResponseDTO.builder()
                .id(maintenance.getId())
                .vehicleId(maintenance.getVehicle().getId())
                .vehicleRegistrationNumber(maintenance.getVehicle().getRegistrationNumber())
                .maintenanceType(maintenance.getMaintenanceType())
                .description(maintenance.getDescription())
                .serviceCenter(maintenance.getServiceCenter())
                .cost(maintenance.getCost())
                .scheduledDate(maintenance.getScheduledDate())
                .completedDate(maintenance.getCompletedDate())
                .status(maintenance.getStatus())
                .build();

    }

}