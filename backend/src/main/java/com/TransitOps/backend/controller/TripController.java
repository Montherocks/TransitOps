package com.TransitOps.backend.controller;

import com.TransitOps.backend.dto.TripCompletionRequest;
import com.TransitOps.backend.dto.TripRequest;
import com.TransitOps.backend.dto.TripResponse;
import com.TransitOps.backend.dto.TripStatusUpdateDTO;
import com.TransitOps.backend.entity.TripStatus;
import com.TransitOps.backend.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(
            @Valid @RequestBody TripRequest request) {

        return new ResponseEntity<>(
                tripService.createTrip(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<TripResponse>> getAllTrips() {

        return ResponseEntity.ok(
                tripService.getAllTrips()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> getTripById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                tripService.getTripById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> updateTrip(
            @PathVariable Long id,
            @Valid @RequestBody TripRequest request) {

        return ResponseEntity.ok(
                tripService.updateTrip(id, request)
        );
    }

    @PatchMapping("/{id}/dispatch")
    public ResponseEntity<TripResponse> dispatchTrip(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                tripService.dispatchTrip(id)
        );
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TripResponse> completeTrip(
            @PathVariable Long id,
            @Valid @RequestBody TripCompletionRequest request) {

        return ResponseEntity.ok(
                tripService.completeTrip(id, request)
        );
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<TripResponse> cancelTrip(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                tripService.cancelTrip(id)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TripResponse> updateTripStatus(
            @PathVariable Long id,
            @Valid @RequestBody TripStatusUpdateDTO request) {

        return ResponseEntity.ok(
                tripService.updateTripStatus(id, request)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TripResponse>> getTripsByStatus(
            @PathVariable TripStatus status) {

        return ResponseEntity.ok(
                tripService.getTripsByStatus(status)
        );
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<TripResponse>> getTripsByVehicle(
            @PathVariable Long vehicleId) {

        return ResponseEntity.ok(
                tripService.getTripsByVehicle(vehicleId)
        );
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<TripResponse>> getTripsByDriver(
            @PathVariable Long driverId) {

        return ResponseEntity.ok(
                tripService.getTripsByDriver(driverId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrip(
            @PathVariable Long id) {

        tripService.deleteTrip(id);

        return ResponseEntity.ok("Trip deleted successfully.");
    }
}