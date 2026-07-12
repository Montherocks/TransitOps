package com.TransitOps.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne
    private User user;

    private Instant expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public PasswordResetToken(String token, User user, Instant expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public PasswordResetToken(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String token, User user, Instant expiryDate) {
        super(id, createdAt, updatedAt);
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
}