package com.TransitOps.backend.config;

import com.TransitOps.backend.entity.Permissions;
import com.TransitOps.backend.repository.PermissionsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PermissionInitializer implements CommandLineRunner {

    private final PermissionsRepository permissionRepository;

    public PermissionInitializer(PermissionsRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) {

        createPermission("FLEET_VIEW", "FLEET", "VIEW");
        createPermission("FLEET_MANAGE", "FLEET", "MANAGE");

        createPermission("DRIVER_VIEW", "DRIVER", "VIEW");
        createPermission("DRIVER_MANAGE", "DRIVER", "MANAGE");

        createPermission("TRIP_VIEW", "TRIP", "VIEW");
        createPermission("TRIP_MANAGE", "TRIP", "MANAGE");

        createPermission("FUEL_VIEW", "FUEL", "VIEW");
        createPermission("FUEL_MANAGE", "FUEL", "MANAGE");

        createPermission("ANALYTICS_VIEW", "ANALYTICS", "VIEW");
    }

    private void createPermission(String name, String module, String action) {

        if (permissionRepository.existsByName(name)) {
            return;
        }

        Permissions permission = new Permissions();
        permission.setName(name);
        permission.setModule(module);
        permission.setAction(action);

        permissionRepository.save(permission);

        System.out.println("Created Permission : " + name);
    }
}