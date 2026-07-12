package com.TransitOps.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequestDTO {

    @NotBlank(message = "Driver name is required")
    private String fullName;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @NotBlank(message = "License category is required")
    private String licenseCategory;

    @Future(message = "License expiry must be a future date")
    private LocalDate licenseExpiry;

    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String phoneNumber;

    @Min(0)
    @Max(100)
    private Integer safetyScore;
}