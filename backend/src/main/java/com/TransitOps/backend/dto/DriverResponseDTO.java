package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.DriverStatus;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverResponseDTO {

    private Long id;
    private String fullName;
    private String licenseNumber;
    private String licenseCategory;
    private LocalDate licenseExpiry;
    private String phoneNumber;
    private Integer safetyScore;
    private DriverStatus status;

}