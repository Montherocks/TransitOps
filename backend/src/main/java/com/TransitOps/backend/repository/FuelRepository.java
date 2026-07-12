package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface FuelRepository extends JpaRepository<FuelLog, Long>, JpaSpecificationExecutor<FuelLog> {

    List<FuelLog> findByVehicleId(Long vehicleId);

    List<FuelLog> findByTripId(Long tripId);

    List<FuelLog> findByFuelDateBetween(LocalDate from, LocalDate to);

}
