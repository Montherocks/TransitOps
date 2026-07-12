package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.VehicleStatus;
import com.TransitOps.backend.entity.VehicleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;

    @NotNull(message = "Maximum load capacity is required")
    @Min(value = 0, message = "Maximum load capacity cannot be negative")
    private Double maxLoadCapacity;

    @NotNull(message = "Odometer reading is required")
    @Min(value = 0, message = "Odometer cannot be negative")
    private Double odometer;

    @NotNull(message = "Acquisition cost is required")
    @Min(value = 0, message = "Acquisition cost cannot be negative")
    private Double acquisitionCost;

    @NotNull(message = "Vehicle status is required")
    private VehicleStatus status;

}