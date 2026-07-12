package com.TransitOps.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expense extends BaseEntity {

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    private Double amount;

    private String description;

    public Expense() {
    }

    public Expense(Vehicle vehicle, Trip trip, ExpenseType type, Double amount, String description) {
        this.vehicle = vehicle;
        this.trip = trip;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public Expense(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Vehicle vehicle, Trip trip, ExpenseType type, Double amount, String description) {
        super(id, createdAt, updatedAt);
        this.vehicle = vehicle;
        this.trip = trip;
        this.type = type;
        this.amount = amount;
        this.description = description;
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

    public ExpenseType getType() {
        return type;
    }

    public void setType(ExpenseType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}