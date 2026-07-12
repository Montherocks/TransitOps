package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.VehicleStatusUpdateDTO;
import com.TransitOps.backend.dto.VehicleRequest;
import com.TransitOps.backend.dto.VehicleResponse;
import com.TransitOps.backend.entity.Vehicle;
import com.TransitOps.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleResponse createVehicle(VehicleRequest request) {

        if (vehicleRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new RuntimeException("Vehicle with this registration number already exists.");
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setModel(request.getModel());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setMaxLoadCapacity(request.getMaxLoadCapacity());
        vehicle.setOdometer(request.getOdometer());
        vehicle.setAcquisitionCost(request.getAcquisitionCost());
        vehicle.setStatus(request.getStatus());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(savedVehicle);
    }

    public List<VehicleResponse> getAllVehicles() {

        return vehicleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public VehicleResponse getVehicleById(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found."));

        return mapToResponse(vehicle);
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {

        return VehicleResponse.builder()
                .id(vehicle.getId())
                .registrationNumber(vehicle.getRegistrationNumber())
                .model(vehicle.getModel())
                .vehicleType(vehicle.getVehicleType())
                .maxLoadCapacity(vehicle.getMaxLoadCapacity())
                .odometer(vehicle.getOdometer())
                .acquisitionCost(vehicle.getAcquisitionCost())
                .status(vehicle.getStatus())
                .createdAt(vehicle.getCreatedAt())
                .updatedAt(vehicle.getUpdatedAt())
                .build();
    }
    public VehicleResponse updateVehicle(Long id, VehicleRequest request) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found."));

        if (!vehicle.getRegistrationNumber().equals(request.getRegistrationNumber())
                && vehicleRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {

            throw new RuntimeException("Vehicle with this registration number already exists.");
        }

        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setModel(request.getModel());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setMaxLoadCapacity(request.getMaxLoadCapacity());
        vehicle.setOdometer(request.getOdometer());
        vehicle.setAcquisitionCost(request.getAcquisitionCost());
        vehicle.setStatus(request.getStatus());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(updatedVehicle);
    }

    public void deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found."));

        vehicleRepository.delete(vehicle);
    }

    public List<VehicleResponse> getVehiclesByStatus(String status) {

        return vehicleRepository.findByStatus(
                        Enum.valueOf(com.TransitOps.backend.entity.VehicleStatus.class, status.toUpperCase())
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<VehicleResponse> getVehiclesByVehicleType(String vehicleType) {

        return vehicleRepository.findByVehicleType(
                        Enum.valueOf(com.TransitOps.backend.entity.VehicleType.class, vehicleType.toUpperCase())
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<VehicleResponse> searchVehiclesByModel(String model) {

        return vehicleRepository.findByModelContainingIgnoreCase(model)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public VehicleResponse updateVehicleStatus(
        Long id,
        VehicleStatusUpdateDTO request) {

             Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Vehicle not found."));

            vehicle.setStatus(request.getStatus());

            Vehicle updatedVehicle = vehicleRepository.save(vehicle);

            return mapToResponse(updatedVehicle);
    }

}
