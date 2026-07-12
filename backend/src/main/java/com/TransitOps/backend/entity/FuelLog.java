package com.TransitOps.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fuel_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuelLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(nullable = false)
    private LocalDate fuelDate;

    @Positive(message = "Fuel quantity must be greater than zero")
    @Column(nullable = false)
    private Double liters;

    @Positive(message = "Fuel cost must be greater than zero")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

    @NotBlank(message = "Fuel station is required")
    @Column(nullable = false)
    private String fuelStation;

}