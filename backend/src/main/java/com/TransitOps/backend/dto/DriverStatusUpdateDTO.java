package com.TransitOps.backend.dto;


import com.TransitOps.backend.entity.DriverStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverStatusUpdateDTO {

    @NotNull(message = "Status is required")
    private DriverStatus status;

}