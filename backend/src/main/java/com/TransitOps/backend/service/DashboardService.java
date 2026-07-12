package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.DashboardResponse;
import com.TransitOps.backend.entity.DriverStatus;
import com.TransitOps.backend.entity.TripStatus;
import com.TransitOps.backend.entity.VehicleStatus;
import com.TransitOps.backend.repository.DriverRepository;
import com.TransitOps.backend.repository.TripRepository;
import com.TransitOps.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final TripRepository tripRepository;

    public DashboardResponse getDashboardData() {

        long totalVehicles = vehicleRepository.count();
        long availableVehicles =
                vehicleRepository.countByStatus(VehicleStatus.AVAILABLE);
        long activeVehicles =
                vehicleRepository.countByStatus(VehicleStatus.ON_TRIP);
        long vehiclesInMaintenance =
                vehicleRepository.countByStatus(VehicleStatus.IN_SHOP);
        long retiredVehicles =
                vehicleRepository.countByStatus(VehicleStatus.RETIRED);

        long totalDrivers = driverRepository.count();
        long availableDrivers =
                driverRepository.countByStatus(DriverStatus.AVAILABLE);
        long driversOnDuty =
                driverRepository.countByStatus(DriverStatus.ON_TRIP);
        long suspendedDrivers =
                driverRepository.countByStatus(DriverStatus.SUSPENDED);

        long totalTrips = tripRepository.count();
        long activeTrips =
                tripRepository.countByStatus(TripStatus.DISPATCHED);
        long pendingTrips =
                tripRepository.countByStatus(TripStatus.DRAFT);
        long completedTrips =
                tripRepository.countByStatus(TripStatus.COMPLETED);
        long cancelledTrips =
                tripRepository.countByStatus(TripStatus.CANCELLED);

        long operationalVehicles = totalVehicles - retiredVehicles;

        double fleetUtilizationPercentage =
                operationalVehicles == 0
                        ? 0.0
                        : (activeVehicles * 100.0) / operationalVehicles;

        return DashboardResponse.builder()
                .totalVehicles(totalVehicles)
                .activeVehicles(activeVehicles)
                .availableVehicles(availableVehicles)
                .vehiclesInMaintenance(vehiclesInMaintenance)
                .retiredVehicles(retiredVehicles)
                .totalDrivers(totalDrivers)
                .availableDrivers(availableDrivers)
                .driversOnDuty(driversOnDuty)
                .suspendedDrivers(suspendedDrivers)
                .totalTrips(totalTrips)
                .activeTrips(activeTrips)
                .pendingTrips(pendingTrips)
                .completedTrips(completedTrips)
                .cancelledTrips(cancelledTrips)
                .fleetUtilizationPercentage(
                        Math.round(fleetUtilizationPercentage * 100.0) / 100.0
                )
                .build();
    }
}