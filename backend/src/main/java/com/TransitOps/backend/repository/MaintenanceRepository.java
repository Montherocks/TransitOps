package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.Maintenance;
import com.TransitOps.backend.entity.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long>, JpaSpecificationExecutor<Maintenance> {

    Optional<Maintenance> findById(Long id);

    boolean existsByVehicleIdAndStatus(Long vehicleId, MaintenanceStatus status);

}