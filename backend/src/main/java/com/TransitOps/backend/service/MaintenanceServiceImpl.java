package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.CreateMaintenanceRequestDTO;
import com.TransitOps.backend.dto.MaintenanceResponseDTO;
import com.TransitOps.backend.dto.MaintenanceStatisticsDTO;
import com.TransitOps.backend.dto.UpdateMaintenanceRequestDTO;
import com.TransitOps.backend.entity.Maintenance;
import com.TransitOps.backend.entity.MaintenanceStatus;
import com.TransitOps.backend.entity.Vehicle;
import com.TransitOps.backend.entity.VehicleStatus;
import com.TransitOps.backend.exception.MaintenanceNotFoundException;
import com.TransitOps.backend.exception.VehicleAlreadyInMaintenanceException;
import com.TransitOps.backend.mapper.MaintenanceMapper;
import com.TransitOps.backend.repository.MaintenanceRepository;
import com.TransitOps.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public MaintenanceResponseDTO createMaintenance(CreateMaintenanceRequestDTO request) {

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));

        if (vehicle.getStatus() == VehicleStatus.RETIRED) {

            throw new RuntimeException(
                    "Retired vehicle cannot be serviced."
            );

        }

        boolean exists = maintenanceRepository.existsByVehicleIdAndStatus(vehicle.getId(), MaintenanceStatus.OPEN);

        if (exists) {

            throw new VehicleAlreadyInMaintenanceException(
                    "Vehicle already has an active maintenance."
            );

        }

        Maintenance maintenance = Maintenance.builder()
                .vehicle(vehicle)
                .maintenanceType(request.getMaintenanceType())
                .description(request.getDescription())
                .serviceCenter(request.getServiceCenter())
                .cost(request.getCost())
                .scheduledDate(request.getScheduledDate())
                .status(MaintenanceStatus.OPEN)
                .build();

        Maintenance saved = maintenanceRepository.save(maintenance);

        vehicle.setStatus(VehicleStatus.IN_SHOP);

        vehicleRepository.save(vehicle);

        return MaintenanceMapper.toDTO(saved);

    }

    @Override
    @Transactional(readOnly = true)
    public MaintenanceResponseDTO getMaintenanceById(Long id) {

        Maintenance maintenance = maintenanceRepository.findById(id)
                        .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance not found."));

        return MaintenanceMapper.toDTO(maintenance);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<MaintenanceResponseDTO> getAllMaintenance(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return maintenanceRepository.findAll(pageable)
                .map(MaintenanceMapper::toDTO);

    }

    @Override
    public MaintenanceResponseDTO updateMaintenance(Long id, UpdateMaintenanceRequestDTO request) {

        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new MaintenanceNotFoundException("Maintenance not found"));

        maintenance.setMaintenanceType(request.getMaintenanceType());
        maintenance.setDescription(request.getDescription());
        maintenance.setServiceCenter(request.getServiceCenter());
        maintenance.setCost(request.getCost());
        maintenance.setScheduledDate(request.getScheduledDate());
        Maintenance updated = maintenanceRepository.save(maintenance);

        return MaintenanceMapper.toDTO(updated);

    }

    @Override
    public MaintenanceResponseDTO completeMaintenance(Long id) {

        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new MaintenanceNotFoundException("Maintenance not found"));

        maintenance.setStatus(MaintenanceStatus.COMPLETED);
        maintenance.setCompletedDate(java.time.LocalDate.now());
        Vehicle vehicle = maintenance.getVehicle();
        if (vehicle.getStatus() != VehicleStatus.RETIRED) {

            vehicle.setStatus(VehicleStatus.AVAILABLE);
            vehicleRepository.save(vehicle);

        }
        Maintenance updated = maintenanceRepository.save(maintenance);

        return MaintenanceMapper.toDTO(updated);

    }

    @Override
    public void deleteMaintenance(Long id) {

        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new MaintenanceNotFoundException("Maintenance not found"));

        maintenanceRepository.delete(maintenance);

    }

    @Override
    @Transactional(readOnly = true)
    public MaintenanceStatisticsDTO getStatistics() {

        long total = maintenanceRepository.count();

        long completed = maintenanceRepository.findAll()
                        .stream()
                        .filter(m -> m.getStatus() == MaintenanceStatus.COMPLETED)
                        .count();

        long open = maintenanceRepository.findAll()
                        .stream()
                        .filter(m -> m.getStatus() == MaintenanceStatus.OPEN)
                        .count();

        BigDecimal totalCost = maintenanceRepository.findAll()
                        .stream()
                        .map(Maintenance::getCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return MaintenanceStatisticsDTO.builder()
                .totalMaintenance(total)
                .completedMaintenance(completed)
                .openMaintenance(open)
                .totalMaintenanceCost(totalCost)
                .build();

    }
}