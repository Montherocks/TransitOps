package com.TransitOps.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Entity
@Table(
        name = "drivers",
        indexes = {
                @Index(name = "idx_driver_license", columnList = "licenseNumber"),
                @Index(name = "idx_driver_phone", columnList = "phoneNumber")
        }
)
public class Driver extends BaseEntity {

    @NotBlank(message = "Driver name is required")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "License number is required")
    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @NotBlank(message = "License category is required")
    @Column(nullable = false)
    private String licenseCategory;

    @Future(message = "License expiry must be a future date")
    @Column(nullable = false)
    private LocalDate licenseExpiry;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be valid")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Min(value = 0)
    @Max(value = 100)
    @Builder.Default
    private Integer safetyScore = 100;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private DriverStatus status = DriverStatus.AVAILABLE;

    public Driver() {
    }

    public Driver(String fullName, String licenseNumber, String licenseCategory, LocalDate licenseExpiry, String phoneNumber, Integer safetyScore, DriverStatus status) {
        this.fullName = fullName;
        this.licenseNumber = licenseNumber;
        this.licenseCategory = licenseCategory;
        this.licenseExpiry = licenseExpiry;
        this.phoneNumber = phoneNumber;
        this.safetyScore = safetyScore;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public LocalDate getLicenseExpiry() {
        return licenseExpiry;
    }

    public void setLicenseExpiry(LocalDate licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(Integer safetyScore) {
        this.safetyScore = safetyScore;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }
}