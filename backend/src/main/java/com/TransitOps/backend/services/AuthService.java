package com.TransitOps.backend.service;

import com.TransitOps.backend.dto.LoginRequest;
import com.TransitOps.backend.dto.LoginResponse;
import com.TransitOps.backend.dto.RefreshTokenRequest;
import com.TransitOps.backend.dto.RefreshTokenResponse;
import com.TransitOps.backend.dto.RegisterRequest;
import com.TransitOps.backend.dto.RegisterResponse;
import com.TransitOps.backend.entity.RefreshToken;
import com.TransitOps.backend.entity.Role;
import com.TransitOps.backend.entity.User;
import com.TransitOps.backend.repository.RoleRepository;
import com.TransitOps.backend.repository.UserRepository;
import com.TransitOps.backend.security.CustomUserDetails;
import com.TransitOps.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered.");
        }

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() ->
                        new RuntimeException("Role not found."));

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setEnabled(true);
        user.setEmailVerified(true);

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .userId(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().getName())
                .message("User registered successfully.")
                .build();
    }
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        String accessToken = jwtService.generateToken(userDetails);

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }

    public RefreshTokenResponse refreshToken(
            RefreshTokenRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.findByToken(
                        request.getRefreshToken()
                );

        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();

        String accessToken =
                jwtService.generateToken(
                        new CustomUserDetails(user)
                );

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .build();
    }

    public void logout(String refreshToken) {

        RefreshToken token =
                refreshTokenService.findByToken(refreshToken);

        refreshTokenService.delete(token);
    }
}