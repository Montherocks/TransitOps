package com.TransitOps.backend.controller;

import com.TransitOps.backend.dto.*;
import com.TransitOps.backend.service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<ApiResponse<MaintenanceResponseDTO>> createMaintenance(
            @Valid @RequestBody CreateMaintenanceRequestDTO request) {

        MaintenanceResponseDTO response = maintenanceService.createMaintenance(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<MaintenanceResponseDTO>builder()
                        .success(true)
                        .message("Maintenance created successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceResponseDTO>> getMaintenanceById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<MaintenanceResponseDTO>builder()
                        .success(true)
                        .message("Maintenance fetched successfully.")
                        .data(maintenanceService.getMaintenanceById(id))
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<MaintenanceResponseDTO>>> getAllMaintenance(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Page<MaintenanceResponseDTO> response =
                maintenanceService.getAllMaintenance(
                        page,
                        size,
                        sortBy,
                        direction
                );

        return ResponseEntity.ok(
                ApiResponse.<Page<MaintenanceResponseDTO>>builder()
                        .success(true)
                        .message("Maintenance records fetched successfully.")
                        .data(response)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceResponseDTO>> updateMaintenance(
            @PathVariable Long id,
            @Valid
            @RequestBody
            UpdateMaintenanceRequestDTO request) {

        return ResponseEntity.ok(
                ApiResponse.<MaintenanceResponseDTO>builder()
                        .success(true)
                        .message("Maintenance updated successfully.")
                        .data(maintenanceService.updateMaintenance(id, request))
                        .build()

        );

    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<ApiResponse<MaintenanceResponseDTO>> completeMaintenance(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<MaintenanceResponseDTO>builder()
                        .success(true)
                        .message("Maintenance completed successfully.")
                        .data(maintenanceService.completeMaintenance(id))
                        .build()
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMaintenance(@PathVariable Long id) {

        maintenanceService.deleteMaintenance(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Maintenance deleted successfully.")
                        .build()

        );

    }

    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<MaintenanceStatisticsDTO>> statistics() {

        return ResponseEntity.ok(
                ApiResponse.<MaintenanceStatisticsDTO>builder()
                        .success(true)
                        .message("Statistics fetched successfully.")
                        .data(maintenanceService.getStatistics())
                        .build()

        );

    }

}