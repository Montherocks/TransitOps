package com.TransitOps.backend.controller;

import com.TransitOps.backend.dto.VehicleRequest;
import com.TransitOps.backend.dto.VehicleResponse;
import com.TransitOps.backend.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(
            @Valid @RequestBody VehicleRequest request) {

        return new ResponseEntity<>(
                vehicleService.createVehicle(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {

        return ResponseEntity.ok(
                vehicleService.getAllVehicles()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                vehicleService.getVehicleById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody VehicleRequest request) {

        return ResponseEntity.ok(
                vehicleService.updateVehicle(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(
            @PathVariable Long id) {

        vehicleService.deleteVehicle(id);

        return ResponseEntity.ok("Vehicle deleted successfully.");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                vehicleService.getVehiclesByStatus(status)
        );
    }

    @GetMapping("/type/{vehicleType}")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByVehicleType(
            @PathVariable String vehicleType) {

        return ResponseEntity.ok(
                vehicleService.getVehiclesByVehicleType(vehicleType)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<VehicleResponse>> searchVehiclesByModel(
            @RequestParam String model) {

        return ResponseEntity.ok(
                vehicleService.searchVehiclesByModel(model)
        );
    }

}