package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.TripCompletionRequest;
import com.TransitOps.backend.dto.TripRequest;
import com.TransitOps.backend.dto.TripResponse;
import com.TransitOps.backend.dto.TripStatusUpdateDTO;
import com.TransitOps.backend.entity.Driver;
import com.TransitOps.backend.entity.DriverStatus;
import com.TransitOps.backend.entity.Trip;
import com.TransitOps.backend.entity.TripStatus;
import com.TransitOps.backend.entity.Vehicle;
import com.TransitOps.backend.entity.VehicleStatus;
import com.TransitOps.backend.repository.DriverRepository;
import com.TransitOps.backend.repository.TripRepository;
import com.TransitOps.backend.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    @Transactional
    public TripResponse createTrip(TripRequest request) {

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found."));

        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found."));

        validateCargoCapacity(vehicle, request.getCargoWeight());

        Trip trip = new Trip();

        trip.setVehicle(vehicle);
        trip.setDriver(driver);
        trip.setSource(request.getSource());
        trip.setDestination(request.getDestination());
        trip.setCargoWeight(request.getCargoWeight());
        trip.setPlannedDistance(request.getPlannedDistance());
        trip.setStartOdometer(vehicle.getOdometer());
        trip.setStatus(TripStatus.DRAFT);

        return mapToResponse(tripRepository.save(trip));
    }

    public List<TripResponse> getAllTrips() {

        return tripRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TripResponse getTripById(Long id) {

        return mapToResponse(findTripById(id));
    }

    @Transactional
    public TripResponse updateTrip(Long id, TripRequest request) {

        Trip trip = findTripById(id);

        if (trip.getStatus() != TripStatus.DRAFT) {
            throw new RuntimeException("Only draft trips can be updated.");
        }

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found."));

        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found."));

        validateCargoCapacity(vehicle, request.getCargoWeight());

        trip.setVehicle(vehicle);
        trip.setDriver(driver);
        trip.setSource(request.getSource());
        trip.setDestination(request.getDestination());
        trip.setCargoWeight(request.getCargoWeight());
        trip.setPlannedDistance(request.getPlannedDistance());
        trip.setStartOdometer(vehicle.getOdometer());

        return mapToResponse(tripRepository.save(trip));
    }

    @Transactional
    public TripResponse dispatchTrip(Long id) {

        Trip trip = findTripById(id);

        if (trip.getStatus() != TripStatus.DRAFT) {
            throw new RuntimeException("Only draft trips can be dispatched.");
        }

        Vehicle vehicle = trip.getVehicle();
        Driver driver = trip.getDriver();

        validateVehicleForDispatch(vehicle);
        validateDriverForDispatch(driver);
        validateCargoCapacity(vehicle, trip.getCargoWeight());

        trip.setStartOdometer(vehicle.getOdometer());
        trip.setStatus(TripStatus.DISPATCHED);

        vehicle.setStatus(VehicleStatus.ON_TRIP);
        driver.setStatus(DriverStatus.ON_TRIP);

        vehicleRepository.save(vehicle);
        driverRepository.save(driver);

        return mapToResponse(tripRepository.save(trip));
    }

    @Transactional
    public TripResponse completeTrip(
            Long id,
            TripCompletionRequest request) {

        Trip trip = findTripById(id);

        if (trip.getStatus() != TripStatus.DISPATCHED) {
            throw new RuntimeException("Only dispatched trips can be completed.");
        }

        if (trip.getStartOdometer() == null) {
            throw new RuntimeException("Trip start odometer is missing.");
        }

        if (request.getEndOdometer() < trip.getStartOdometer()) {
            throw new RuntimeException(
                    "End odometer cannot be less than start odometer."
            );
        }

        double actualDistance =
                request.getEndOdometer() - trip.getStartOdometer();

        Vehicle vehicle = trip.getVehicle();
        Driver driver = trip.getDriver();

        trip.setEndOdometer(request.getEndOdometer());
        trip.setActualDistance(actualDistance);
        trip.setFuelUsed(request.getFuelUsed());
        trip.setStatus(TripStatus.COMPLETED);

        vehicle.setOdometer(request.getEndOdometer());

        if (vehicle.getStatus() != VehicleStatus.RETIRED) {
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }

        if (driver.getStatus() != DriverStatus.SUSPENDED) {
            driver.setStatus(DriverStatus.AVAILABLE);
        }

        vehicleRepository.save(vehicle);
        driverRepository.save(driver);

        return mapToResponse(tripRepository.save(trip));
    }

    @Transactional
    public TripResponse cancelTrip(Long id) {

        Trip trip = findTripById(id);

        if (trip.getStatus() == TripStatus.COMPLETED) {
            throw new RuntimeException("Completed trips cannot be cancelled.");
        }

        if (trip.getStatus() == TripStatus.CANCELLED) {
            throw new RuntimeException("Trip is already cancelled.");
        }

        if (trip.getStatus() == TripStatus.DISPATCHED) {

            Vehicle vehicle = trip.getVehicle();
            Driver driver = trip.getDriver();

            if (vehicle.getStatus() != VehicleStatus.RETIRED) {
                vehicle.setStatus(VehicleStatus.AVAILABLE);
            }

            if (driver.getStatus() != DriverStatus.SUSPENDED) {
                driver.setStatus(DriverStatus.AVAILABLE);
            }

            vehicleRepository.save(vehicle);
            driverRepository.save(driver);
        }

        trip.setStatus(TripStatus.CANCELLED);

        return mapToResponse(tripRepository.save(trip));
    }

    @Transactional
    public TripResponse updateTripStatus(
            Long id,
            TripStatusUpdateDTO request) {

        if (request.getStatus() == TripStatus.DISPATCHED) {
            return dispatchTrip(id);
        }

        if (request.getStatus() == TripStatus.CANCELLED) {
            return cancelTrip(id);
        }

        if (request.getStatus() == TripStatus.COMPLETED) {
            throw new RuntimeException(
                    "Use the trip completion endpoint to complete a trip."
            );
        }

        Trip trip = findTripById(id);

        if (trip.getStatus() != TripStatus.DRAFT) {
            throw new RuntimeException(
                    "Only a draft trip can remain in draft status."
            );
        }

        return mapToResponse(trip);
    }

    public List<TripResponse> getTripsByStatus(TripStatus status) {

        return tripRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<TripResponse> getTripsByVehicle(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found."));

        return tripRepository.findByVehicle(vehicle)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<TripResponse> getTripsByDriver(Long driverId) {

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found."));

        return tripRepository.findByDriver(driver)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public void deleteTrip(Long id) {

        Trip trip = findTripById(id);

        if (trip.getStatus() == TripStatus.DISPATCHED) {
            throw new RuntimeException(
                    "A dispatched trip cannot be deleted. Cancel it first."
            );
        }

        tripRepository.delete(trip);
    }

    private Trip findTripById(Long id) {

        return tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found."));
    }

    private void validateVehicleForDispatch(Vehicle vehicle) {

        if (vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new RuntimeException(
                    "Vehicle is not available for dispatch."
            );
        }
    }

    private void validateDriverForDispatch(Driver driver) {

        if (driver.getStatus() != DriverStatus.AVAILABLE) {
            throw new RuntimeException(
                    "Driver is not available for dispatch."
            );
        }

        if (driver.getLicenseExpiry() == null) {
            throw new RuntimeException(
                    "Driver license expiry date is missing."
            );
        }

        if (driver.getLicenseExpiry().isBefore(LocalDate.now())) {
            throw new RuntimeException(
                    "Driver license has expired."
            );
        }
    }

    private void validateCargoCapacity(
            Vehicle vehicle,
            Double cargoWeight) {

        if (cargoWeight == null || cargoWeight < 0) {
            throw new RuntimeException("Cargo weight is invalid.");
        }

        if (vehicle.getMaxLoadCapacity() == null) {
            throw new RuntimeException(
                    "Vehicle maximum load capacity is missing."
            );
        }

        if (cargoWeight > vehicle.getMaxLoadCapacity()) {
            throw new RuntimeException(
                    "Cargo weight exceeds vehicle maximum load capacity."
            );
        }
    }

    private TripResponse mapToResponse(Trip trip) {

        return TripResponse.builder()
                .id(trip.getId())
                .vehicleId(
                        trip.getVehicle() != null
                                ? trip.getVehicle().getId()
                                : null
                )
                .vehicleRegistrationNumber(
                        trip.getVehicle() != null
                                ? trip.getVehicle().getRegistrationNumber()
                                : null
                )
                .driverId(
                        trip.getDriver() != null
                                ? trip.getDriver().getId()
                                : null
                )
                .driverName(
                        trip.getDriver() != null
                                ? trip.getDriver().getFullName()
                                : null
                )
                .source(trip.getSource())
                .destination(trip.getDestination())
                .cargoWeight(trip.getCargoWeight())
                .plannedDistance(trip.getPlannedDistance())
                .actualDistance(trip.getActualDistance())
                .startOdometer(trip.getStartOdometer())
                .endOdometer(trip.getEndOdometer())
                .fuelUsed(trip.getFuelUsed())
                .status(trip.getStatus())
                .createdAt(trip.getCreatedAt())
                .updatedAt(trip.getUpdatedAt())
                .build();
    }
}