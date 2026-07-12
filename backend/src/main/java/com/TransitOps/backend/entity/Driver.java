package com.TransitOps.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "drivers")
public class Driver extends BaseEntity {

    private String fullName;

    @Column(unique = true)
    private String licenseNumber;

    private String licenseCategory;

    private LocalDate licenseExpiry;

    private String phoneNumber;

    private Integer safetyScore;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

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