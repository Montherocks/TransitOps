package com.TransitOps.backend.service;


import com.TransitOps.backend.dto.DriverRequestDTO;
import com.TransitOps.backend.dto.DriverResponseDTO;
import com.TransitOps.backend.dto.DriverStatusUpdateDTO;
import org.springframework.data.domain.Page;

public interface DriverService {

    DriverResponseDTO registerDriver(DriverRequestDTO request);

    DriverResponseDTO getDriverById(Long id);

    Page<DriverResponseDTO> getAllDrivers(int page, int size, String sortBy, String direction);

    DriverResponseDTO updateDriver(Long id, DriverRequestDTO request);

    DriverResponseDTO updateStatus(Long id, DriverStatusUpdateDTO request);

    void deleteDriver(Long id);

}