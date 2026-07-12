package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {

    private Long id;

    private Long vehicleId;

    private String vehicleRegistrationNumber;

    private Long driverId;

    private String driverName;

    private String source;

    private String destination;

    private Double cargoWeight;

    private Double plannedDistance;

    private Double actualDistance;

    private Double startOdometer;

    private Double endOdometer;

    private Double fuelUsed;

    private TripStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}