package com.TransitOps.backend.controller;

import com.TransitOps.backend.dto.*;
import com.TransitOps.backend.service.FuelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fuel")
@RequiredArgsConstructor
public class FuelController {

    private final FuelService fuelService;

    @PostMapping
    public ResponseEntity<ApiResponse<FuelLogResponseDTO>> createFuelLog(@Valid @RequestBody CreateFuelLogRequestDTO request){

        FuelLogResponseDTO response = fuelService.createFuelLog(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<FuelLogResponseDTO>builder()
                        .success(true)
                        .message("Fuel log created successfully.")
                        .data(response)
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FuelLogResponseDTO>> getFuelLogById(@PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<FuelLogResponseDTO>builder()
                        .success(true)
                        .message("Fuel log fetched successfully.")
                        .data(fuelService.getFuelLogById(id))
                        .build()

        );

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<FuelLogResponseDTO>>> getAllFuelLogs(

            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size,
            @RequestParam(defaultValue = "id")
            String sortBy,
            @RequestParam(defaultValue = "asc")
            String direction
    ){

        return ResponseEntity.ok(
                ApiResponse.<Page<FuelLogResponseDTO>>builder()
                        .success(true)
                        .message("Fuel logs fetched successfully.")
                        .data(fuelService.getAllFuelLogs(
                                page,
                                size,
                                sortBy,
                                direction
                        ))
                        .build()

        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FuelLogResponseDTO>> updateFuelLog(
            @PathVariable Long id,
            @Valid
            @RequestBody
            UpdateFuelLogRequestDTO request
    ){

        return ResponseEntity.ok(
                ApiResponse.<FuelLogResponseDTO>builder()
                        .success(true)
                        .message("Fuel log updated successfully.")
                        .data(fuelService.updateFuelLog(id,request))
                        .build()
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFuelLog(@PathVariable Long id){

        fuelService.deleteFuelLog(id);
        return ResponseEntity.ok(

                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Fuel log deleted successfully.")
                        .build()

        );

    }

    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<FuelStatisticsDTO>> statistics(){

        return ResponseEntity.ok(
                ApiResponse.<FuelStatisticsDTO>builder()
                        .success(true)
                        .message("Statistics fetched successfully.")
                        .data(fuelService.getStatistics())
                        .build()

        );

    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<ApiResponse<Page<FuelLogResponseDTO>>> getFuelLogsByVehicle(

            @PathVariable Long vehicleId,
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ){

        return ResponseEntity.ok(
                ApiResponse.<Page<FuelLogResponseDTO>>builder()
                        .success(true)
                        .message("Vehicle fuel logs fetched successfully.")
                        .data(
                                fuelService.getFuelLogsByVehicle(
                                        vehicleId,
                                        page,
                                        size
                                )
                        )
                        .build()
        );

    }

}
