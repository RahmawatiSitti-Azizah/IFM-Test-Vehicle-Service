package com.mitrais.vehicle_service_system.dto;

import lombok.Data;

@Data
public class BaseOperationRequest {
    private String brand;
    private String model;
    private String engine;
    private Integer yearStart;
    private Integer yearEnd;
    private Double distanceStart;
    private Double distanceEnd;
    private String name;
    private Double approxCost;
    private String description;
    private Integer time;
}
