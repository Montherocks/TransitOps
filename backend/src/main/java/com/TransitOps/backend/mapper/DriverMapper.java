package com.TransitOps.backend.mapper;

import com.TransitOps.backend.dto.DriverRequestDTO;
import com.TransitOps.backend.dto.DriverResponseDTO;
import com.TransitOps.backend.entity.Driver;

public class DriverMapper {

    private DriverMapper() {
    }

    public static Driver toEntity(DriverRequestDTO dto) {

        return Driver.builder()
                .fullName(dto.getFullName())
                .licenseNumber(dto.getLicenseNumber())
                .licenseCategory(dto.getLicenseCategory())
                .licenseExpiry(dto.getLicenseExpiry())
                .phoneNumber(dto.getPhoneNumber())
                .safetyScore(dto.getSafetyScore() == null ? 100 : dto.getSafetyScore())
                .build();
    }

    public static DriverResponseDTO toDTO(Driver driver) {

        return DriverResponseDTO.builder()
                .id(driver.getId())
                .fullName(driver.getFullName())
                .licenseNumber(driver.getLicenseNumber())
                .licenseCategory(driver.getLicenseCategory())
                .licenseExpiry(driver.getLicenseExpiry())
                .phoneNumber(driver.getPhoneNumber())
                .safetyScore(driver.getSafetyScore())
                .status(driver.getStatus())
                .build();
    }
}