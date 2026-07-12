package com.TransitOps.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuelEfficiencyDTO {

    private Long vehicleId;

    private String registrationNumber;

    private Double totalDistance;

    private Double totalFuel;

    private Double kmPerLiter;

}