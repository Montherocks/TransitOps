package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.Driver;
import com.TransitOps.backend.entity.Trip;
import com.TransitOps.backend.entity.TripStatus;
import com.TransitOps.backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByStatus(TripStatus status);

    List<Trip> findByVehicle(Vehicle vehicle);

    List<Trip> findByDriver(Driver driver);

    List<Trip> findBySourceContainingIgnoreCase(String source);

    List<Trip> findByDestinationContainingIgnoreCase(String destination);

}