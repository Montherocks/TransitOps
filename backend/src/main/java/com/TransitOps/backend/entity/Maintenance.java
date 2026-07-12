package com.TransitOps.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_logs")
public class Maintenance extends BaseEntity {

    @ManyToOne
    private Vehicle vehicle;

    private String serviceType;

    private String description;

    private Double cost;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    public Maintenance() {
    }

    public Maintenance(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Maintenance(Vehicle vehicle, String serviceType, String description, Double cost, MaintenanceStatus status) {
        this.vehicle = vehicle;
        this.serviceType = serviceType;
        this.description = description;
        this.cost = cost;
        this.status = status;
    }

    public Maintenance(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Vehicle vehicle, String serviceType, String description, Double cost, MaintenanceStatus status) {
        super(id, createdAt, updatedAt);
        this.vehicle = vehicle;
        this.serviceType = serviceType;
        this.description = description;
        this.cost = cost;
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public MaintenanceStatus getStatus() {
        return status;
    }

    public void setStatus(MaintenanceStatus status) {
        this.status = status;
    }
}