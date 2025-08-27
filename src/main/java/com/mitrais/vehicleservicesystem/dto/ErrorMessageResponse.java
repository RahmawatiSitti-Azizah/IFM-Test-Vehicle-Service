package com.mitrais.vehicleservicesystem.dto;

import lombok.Data;

@Data
public class ErrorMessageResponse {
    private String message;

    public ErrorMessageResponse(String message) {
        this.message = message;
    }
}
