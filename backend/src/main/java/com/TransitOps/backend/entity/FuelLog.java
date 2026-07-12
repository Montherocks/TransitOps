package com.TransitOps.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fuel_logs")
public class FuelLog extends BaseEntity {

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Trip trip;

    private Double liters;

    private Double cost;

    private LocalDate fuelDate;

    public FuelLog(Vehicle vehicle, Trip trip, Double liters, Double cost, LocalDate fuelDate) {
        this.vehicle = vehicle;
        this.trip = trip;
        this.liters = liters;
        this.cost = cost;
        this.fuelDate = fuelDate;
    }

    public FuelLog(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Vehicle vehicle, Trip trip, Double liters, Double cost, LocalDate fuelDate) {
        super(id, createdAt, updatedAt);
        this.vehicle = vehicle;
        this.trip = trip;
        this.liters = liters;
        this.cost = cost;
        this.fuelDate = fuelDate;
    }

    public FuelLog() {
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Double getLiters() {
        return liters;
    }

    public void setLiters(Double liters) {
        this.liters = liters;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getFuelDate() {
        return fuelDate;
    }

    public void setFuelDate(LocalDate fuelDate) {
        this.fuelDate = fuelDate;
    }
}