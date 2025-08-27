package com.mitrais.vehicleservicesystem.mapper;

import com.mitrais.vehicleservicesystem.dto.BaseVehicleRequest;
import com.mitrais.vehicleservicesystem.entity.Vehicle;

public class VehicleMapper {
    public static Vehicle toEntity(BaseVehicleRequest data) {
        Vehicle result = new Vehicle();
        result.setBrand(data.getBrand());
        result.setModel(data.getModel());
        result.setEngine(data.getEngine());
        result.setMakeYear(data.getMakeYear());
        return result;
    }
}
