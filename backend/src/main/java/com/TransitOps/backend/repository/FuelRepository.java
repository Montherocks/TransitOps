package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.FuelLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FuelRepository extends JpaRepository<FuelLog, Long>, JpaSpecificationExecutor<FuelLog> {

    List<FuelLog> findByVehicleId(Long vehicleId);

    List<FuelLog> findByTripId(Long tripId);

    List<FuelLog> findByFuelDateBetween(LocalDate from, LocalDate to);

    Page<FuelLog> findByVehicleId(Long vehicleId, Pageable pageable);

    @Query("SELECT COALESCE(SUM(f.liters),0) FROM FuelLog f")
    Double getTotalFuel();

    @Query("SELECT COALESCE(SUM(f.cost),0) FROM FuelLog f")
    BigDecimal getTotalCost();

    @Query("SELECT COUNT(f) FROM FuelLog f")
    Long getTotalLogs();

}
