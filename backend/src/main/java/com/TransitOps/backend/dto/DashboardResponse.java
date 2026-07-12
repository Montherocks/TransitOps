package com.TransitOps.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private Long totalVehicles;

    private Long activeVehicles;

    private Long availableVehicles;

    private Long vehiclesInMaintenance;

    private Long retiredVehicles;

    private Long totalDrivers;

    private Long availableDrivers;

    private Long driversOnDuty;

    private Long suspendedDrivers;

    private Long totalTrips;

    private Long activeTrips;

    private Long pendingTrips;

    private Long completedTrips;

    private Long cancelledTrips;

    private Double fleetUtilizationPercentage;
}