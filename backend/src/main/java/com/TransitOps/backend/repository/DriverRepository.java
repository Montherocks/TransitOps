package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.Driver;
import com.TransitOps.backend.entity.DriverStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByLicenseNumber(String licenseNumber);

    Optional<Driver> findByPhoneNumber(String phoneNumber);

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    Page<Driver> findByStatus(DriverStatus status, Pageable pageable);

    Page<Driver> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);

    Page<Driver> findByLicenseCategory(String licenseCategory, Pageable pageable);

    Page<Driver> findByLicenseExpiryBefore(LocalDate date, Pageable pageable);

    long countByStatus(DriverStatus status);

}