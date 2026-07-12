package com.TransitOps.backend.dto;

import com.TransitOps.backend.entity.TripStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripStatusUpdateDTO {

    @NotNull(message = "Status is required")
    private TripStatus status;
}