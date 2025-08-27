package com.mitrais.vehicleservicesystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseVehicleRequest {
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String engine;
    @NotNull
    private Integer makeYear;
}
