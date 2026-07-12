package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.Maintenance;
import com.TransitOps.backend.entity.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long>, JpaSpecificationExecutor<Maintenance> {

    Optional<Maintenance> findById(Long id);

    boolean existsByVehicleIdAndStatus(Long vehicleId, MaintenanceStatus status);

    @Query("""
    SELECT COALESCE(SUM(m.cost),0)
    FROM Maintenance m
    """)
    BigDecimal getTotalMaintenanceCost();

    @Query("""
    SELECT COUNT(m)
    FROM Maintenance m
    WHERE m.status='OPEN'
    """)
    Long getOpenCount();

    @Query("""
    SELECT COUNT(m)
    FROM Maintenance m
    WHERE m.status='COMPLETED'
    """)
    Long getCompletedCount();

}