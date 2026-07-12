package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.VehicleStatus;
import com.TransitOps.backend.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {

    private Long id;

    private String registrationNumber;

    private String model;

    private VehicleType vehicleType;

    private Double maxLoadCapacity;

    private Double odometer;

    private Double acquisitionCost;

    private VehicleStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}