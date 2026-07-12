package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.VehicleStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleStatusUpdateDTO {

    @NotNull(message = "Status is required")
    private VehicleStatus status;

}
