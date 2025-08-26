package com.mitrais.vehicle_service_system.dto;

import lombok.Data;

@Data
public class BaseVehicleRequest {
    private String brand;
    private String model;
    private String engine;
    private Integer makeYear;
}
