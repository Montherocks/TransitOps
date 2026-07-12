package com.TransitOps.backend.controller;


import com.TransitOps.backend.dto.ApiResponse;
import com.TransitOps.backend.dto.DriverRequestDTO;
import com.TransitOps.backend.dto.DriverResponseDTO;
import com.TransitOps.backend.dto.DriverStatusUpdateDTO;
import com.TransitOps.backend.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<ApiResponse<DriverResponseDTO>> registerDriver(@Valid @RequestBody DriverRequestDTO request) {

        DriverResponseDTO response = driverService.registerDriver(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<DriverResponseDTO>builder()
                        .success(true)
                        .message("Driver registered successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverResponseDTO>> getDriverById(@PathVariable Long id) {

        DriverResponseDTO response = driverService.getDriverById(id);

        return ResponseEntity.ok(
                ApiResponse.<DriverResponseDTO>builder()
                        .success(true)
                        .message("Driver fetched successfully.")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DriverResponseDTO>>> getAllDrivers(@RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size,
                                                                              @RequestParam(defaultValue = "id") String sortBy,
                                                                              @RequestParam(defaultValue = "asc") String direction) {

        Page<DriverResponseDTO> response =
                driverService.getAllDrivers(
                        page,
                        size,
                        sortBy,
                        direction
                );

        return ResponseEntity.ok(
                ApiResponse.<Page<DriverResponseDTO>>builder()
                        .success(true)
                        .message("Drivers fetched successfully.")
                        .data(response)
                        .build()
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverResponseDTO>> updateDriver(@PathVariable Long id, @Valid @RequestBody DriverRequestDTO request) {

        DriverResponseDTO response = driverService.updateDriver(id, request);

        return ResponseEntity.ok(

                ApiResponse.<DriverResponseDTO>builder()
                        .success(true)
                        .message("Driver updated successfully.")
                        .data(response)
                        .build()

        );

    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DriverResponseDTO>> updateStatus(@PathVariable Long id, @RequestBody @Valid DriverStatusUpdateDTO request) {

        DriverResponseDTO response = driverService.updateStatus(id, request);

        return ResponseEntity.ok(

                ApiResponse.<DriverResponseDTO>builder()
                        .success(true)
                        .message("Driver status updated successfully.")
                        .data(response)
                        .build()

        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDriver(@PathVariable Long id) {

        driverService.deleteDriver(id);

        return ResponseEntity.ok(

                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Driver deleted successfully.")
                        .build()

        );

    }

}