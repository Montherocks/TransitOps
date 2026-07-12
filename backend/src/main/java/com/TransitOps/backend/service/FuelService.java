package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.CreateFuelLogRequestDTO;
import com.TransitOps.backend.dto.FuelLogResponseDTO;
import com.TransitOps.backend.dto.FuelStatisticsDTO;
import com.TransitOps.backend.dto.UpdateFuelLogRequestDTO;
import org.springframework.data.domain.Page;

public interface FuelService {

    FuelLogResponseDTO createFuelLog(CreateFuelLogRequestDTO request);

    FuelLogResponseDTO getFuelLogById(Long id);

    Page<FuelLogResponseDTO> getAllFuelLogs(
            int page,
            int size,
            String sortBy,
            String direction
    );

    FuelLogResponseDTO updateFuelLog(Long id, UpdateFuelLogRequestDTO request);

    void deleteFuelLog(Long id);

    FuelStatisticsDTO getStatistics();

    Page<FuelLogResponseDTO> getFuelLogsByVehicle(Long vehicleId, int page, int size);

}