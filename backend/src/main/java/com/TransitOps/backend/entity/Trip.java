package com.TransitOps.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Driver driver;

    private String source;

    private String destination;

    private Double cargoWeight;

    private Double plannedDistance;

    private Double actualDistance;

    private Double startOdometer;

    private Double endOdometer;

    private Double fuelUsed;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    public Trip() {
    }

    public Trip(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Trip(Vehicle vehicle, Driver driver, String source, String destination, Double cargoWeight, Double plannedDistance, Double actualDistance, Double startOdometer, Double endOdometer, Double fuelUsed, TripStatus status) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.source = source;
        this.destination = destination;
        this.cargoWeight = cargoWeight;
        this.plannedDistance = plannedDistance;
        this.actualDistance = actualDistance;
        this.startOdometer = startOdometer;
        this.endOdometer = endOdometer;
        this.fuelUsed = fuelUsed;
        this.status = status;
    }

    public Trip(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Vehicle vehicle, Driver driver, String source, String destination, Double cargoWeight, Double plannedDistance, Double actualDistance, Double startOdometer, Double endOdometer, Double fuelUsed, TripStatus status) {
        super(id, createdAt, updatedAt);
        this.vehicle = vehicle;
        this.driver = driver;
        this.source = source;
        this.destination = destination;
        this.cargoWeight = cargoWeight;
        this.plannedDistance = plannedDistance;
        this.actualDistance = actualDistance;
        this.startOdometer = startOdometer;
        this.endOdometer = endOdometer;
        this.fuelUsed = fuelUsed;
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Double getPlannedDistance() {
        return plannedDistance;
    }

    public void setPlannedDistance(Double plannedDistance) {
        this.plannedDistance = plannedDistance;
    }

    public Double getActualDistance() {
        return actualDistance;
    }

    public void setActualDistance(Double actualDistance) {
        this.actualDistance = actualDistance;
    }

    public Double getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(Double startOdometer) {
        this.startOdometer = startOdometer;
    }

    public Double getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(Double endOdometer) {
        this.endOdometer = endOdometer;
    }

    public Double getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(Double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }
}