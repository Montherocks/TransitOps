package com.TransitOps.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    private String model;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private Double maxLoadCapacity;

    private Double odometer;

    private Double acquisitionCost;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    public Vehicle() {
    }

    public Vehicle(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Vehicle(String registrationNumber, String model, VehicleType vehicleType, Double maxLoadCapacity, Double odometer, Double acquisitionCost, VehicleStatus status) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.vehicleType = vehicleType;
        this.maxLoadCapacity = maxLoadCapacity;
        this.odometer = odometer;
        this.acquisitionCost = acquisitionCost;
        this.status = status;
    }

    public Vehicle(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String registrationNumber, String model, VehicleType vehicleType, Double maxLoadCapacity, Double odometer, Double acquisitionCost, VehicleStatus status) {
        super(id, createdAt, updatedAt);
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.vehicleType = vehicleType;
        this.maxLoadCapacity = maxLoadCapacity;
        this.odometer = odometer;
        this.acquisitionCost = acquisitionCost;
        this.status = status;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public void setMaxLoadCapacity(Double maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public Double getOdometer() {
        return odometer;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public Double getAcquisitionCost() {
        return acquisitionCost;
    }

    public void setAcquisitionCost(Double acquisitionCost) {
        this.acquisitionCost = acquisitionCost;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}