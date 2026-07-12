package com.TransitOps.backend.controller;

import com.TransitOps.backend.dto.LoginRequest;
import com.TransitOps.backend.dto.LoginResponse;
import com.TransitOps.backend.dto.RefreshTokenRequest;
import com.TransitOps.backend.dto.RefreshTokenResponse;
import com.TransitOps.backend.dto.RegisterRequest;
import com.TransitOps.backend.dto.RegisterResponse;
import com.TransitOps.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestParam String refreshToken) {

        authService.logout(refreshToken);

        return ResponseEntity.ok("Logged out successfully.");
    }

}