package com.mitrais.vehicle_service_system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseOperationRequest {
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String engine;
    @NotNull
    private Integer yearStart;
    @NotNull
    private Integer yearEnd;
    @NotNull
    private Double distanceStart;
    @NotNull
    private Double distanceEnd;
    @NotNull
    private String name;
    @NotNull
    private Double approxCost;
    private String description;
    @NotNull
    private Integer time;
}
