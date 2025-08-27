package com.mitrais.vehicle_service_system.dto;

import lombok.Data;

@Data
public class ErrorMessageResponse {
    private String message;

    public ErrorMessageResponse(String message) {
        this.message = message;
    }
}
