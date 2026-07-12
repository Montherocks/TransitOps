package com.TransitOps.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripCompletionRequest {

    @NotNull(message = "End odometer reading is required")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "End odometer reading cannot be negative"
    )
    private Double endOdometer;

    @NotNull(message = "Fuel used is required")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Fuel used cannot be negative"
    )
    private Double fuelUsed;
}