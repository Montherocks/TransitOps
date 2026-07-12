package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.MaintenanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceStatusUpdateDTO {

    @NotNull
    private MaintenanceStatus status;

}