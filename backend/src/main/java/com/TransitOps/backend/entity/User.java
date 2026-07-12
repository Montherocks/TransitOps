package com.TransitOps.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email")
        }
)
public class User extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean enabled = true;

    private Boolean emailVerified = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public User(String fullName, String email, String password, Boolean enabled, Boolean emailVerified, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.role = role;
    }

    public User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String fullName, String email, String password, Boolean enabled, Boolean emailVerified, Role role) {
        super(id, createdAt, updatedAt);
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}