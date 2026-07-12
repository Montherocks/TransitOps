package com.TransitOps.backend.config;

import com.TransitOps.backend.entity.Permissions;
import com.TransitOps.backend.entity.Role;
import com.TransitOps.backend.repository.PermissionsRepository;
import com.TransitOps.backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionsRepository permissionRepository;

    public RoleInitializer(RoleRepository roleRepository,
                           PermissionsRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) {

        createRole(
                "FLEET_MANAGER",
                "Full fleet management",
                "FLEET_MANAGE",
                "DRIVER_MANAGE",
                "ANALYTICS_VIEW"
        );

        createRole(
                "DISPATCHER",
                "Dispatch and schedule trips",
                "FLEET_VIEW",
                "TRIP_MANAGE"
        );

        createRole(
                "SAFETY_OFFICER",
                "Monitor drivers and trips",
                "DRIVER_MANAGE",
                "TRIP_VIEW"
        );

        createRole(
                "FINANCIAL_ANALYST",
                "Finance and reports",
                "FLEET_VIEW",
                "FUEL_MANAGE",
                "ANALYTICS_VIEW"
        );
    }

    private void createRole(String name,
                            String description,
                            String... permissionNames) {

        if (roleRepository.existsByName(name)) {
            return;
        }

        Set<Permissions> permissions = new HashSet<>();

        for (String permissionName : permissionNames) {
            permissionRepository.findByName(permissionName)
                    .ifPresent(permissions::add);
        }

        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        role.setPermissions(permissions);

        roleRepository.save(role);
    }
}