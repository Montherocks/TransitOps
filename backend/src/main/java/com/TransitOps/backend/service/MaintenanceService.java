package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.CreateMaintenanceRequestDTO;
import com.TransitOps.backend.dto.MaintenanceResponseDTO;
import com.TransitOps.backend.dto.MaintenanceStatisticsDTO;
import com.TransitOps.backend.dto.UpdateMaintenanceRequestDTO;
import org.springframework.data.domain.Page;

public interface MaintenanceService {

    MaintenanceResponseDTO createMaintenance(CreateMaintenanceRequestDTO request);

    MaintenanceResponseDTO getMaintenanceById(Long id);

    Page<MaintenanceResponseDTO> getAllMaintenance(
            int page,
            int size,
            String sortBy,
            String direction
    );

    MaintenanceResponseDTO updateMaintenance(Long id, UpdateMaintenanceRequestDTO request);

    MaintenanceResponseDTO completeMaintenance(Long id);

    void deleteMaintenance(Long id);

    MaintenanceStatisticsDTO getStatistics();

}