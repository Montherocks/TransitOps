package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.DriverRequestDTO;
import com.TransitOps.backend.dto.DriverResponseDTO;
import com.TransitOps.backend.dto.DriverStatusUpdateDTO;
import com.TransitOps.backend.entity.Driver;
import com.TransitOps.backend.entity.DriverStatus;
import com.TransitOps.backend.exception.DriverNotFoundException;
import com.TransitOps.backend.exception.DuplicateDriverException;
import com.TransitOps.backend.mapper.DriverMapper;
import com.TransitOps.backend.repository.DriverRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public DriverResponseDTO registerDriver(DriverRequestDTO request) {

        if (driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new DuplicateDriverException("License number already exists.");
        }

        if (driverRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateDriverException("Phone number already exists.");
        }

        Driver driver = DriverMapper.toEntity(request);

        Driver savedDriver = driverRepository.save(driver);

        return DriverMapper.toDTO(savedDriver);
    }

    @Override
    @Transactional
    public DriverResponseDTO getDriverById(Long id) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new DriverNotFoundException("Driver not found with id : " + id));

        return DriverMapper.toDTO(driver);
    }

    @Override
    @Transactional
    public Page<DriverResponseDTO> getAllDrivers(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return driverRepository.findAll(pageable)
                .map(DriverMapper::toDTO);
    }

    @Override
    public DriverResponseDTO updateDriver(Long id, DriverRequestDTO request) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new DriverNotFoundException("Driver not found."));

        if (!driver.getLicenseNumber().equals(request.getLicenseNumber())
                && driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {

            throw new DuplicateDriverException("License number already exists.");
        }

        if (!driver.getPhoneNumber().equals(request.getPhoneNumber())
                && driverRepository.existsByPhoneNumber(request.getPhoneNumber())) {

            throw new DuplicateDriverException("Phone number already exists.");
        }

        driver.setFullName(request.getFullName());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setLicenseCategory(request.getLicenseCategory());
        driver.setLicenseExpiry(request.getLicenseExpiry());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setSafetyScore(request.getSafetyScore());

        Driver updatedDriver = driverRepository.save(driver);

        return DriverMapper.toDTO(updatedDriver);
    }

    @Override
    public DriverResponseDTO updateStatus(Long id, DriverStatusUpdateDTO request) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new DriverNotFoundException("Driver not found."));

        driver.setStatus(request.getStatus());

        Driver updatedDriver = driverRepository.save(driver);

        return DriverMapper.toDTO(updatedDriver);
    }

    @Override
    public void deleteDriver(Long id) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new DriverNotFoundException("Driver not found."));

        if(driver.getStatus()== DriverStatus.ON_TRIP){
            throw new IllegalStateException("Driver is on trip.");
        }

        driverRepository.delete(driver);

    }
}