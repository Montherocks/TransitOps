package com.TransitOps.backend.mapper;

import com.TransitOps.backend.dto.FuelLogResponseDTO;
import com.TransitOps.backend.entity.FuelLog;

public class FuelMapper {

    private FuelMapper() {
    }

    public static FuelLogResponseDTO toDTO(FuelLog fuelLog) {

        return FuelLogResponseDTO.builder()
                .id(fuelLog.getId())
                .vehicleId(fuelLog.getVehicle().getId())
                .registrationNumber(fuelLog.getVehicle().getRegistrationNumber())
                .tripId(fuelLog.getTrip() != null ? fuelLog.getTrip().getId() : null)
                .fuelDate(fuelLog.getFuelDate())
                .liters(fuelLog.getLiters())
                .cost(fuelLog.getCost())
                .fuelStation(fuelLog.getFuelStation())
                .build();

    }

}