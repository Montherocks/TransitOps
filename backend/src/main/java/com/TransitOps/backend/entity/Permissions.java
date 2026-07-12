package com.TransitOps.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "permissions")
public class Permissions extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String module;

    private String action;

    public Permissions() {
    }

    public Permissions(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Permissions(String name, String module, String action) {
        this.name = name;
        this.module = module;
        this.action = action;
    }

    public Permissions(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String module, String action) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.module = module;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}