package com.mitrais.vehicle_service_system.mapper;

import com.mitrais.vehicle_service_system.dto.BaseVehicleRequest;
import com.mitrais.vehicle_service_system.entity.Vehicle;

public class VehicleMapper {
    public static Vehicle toEntity(BaseVehicleRequest data){
        Vehicle result = new Vehicle();
        result.setBrand(data.getBrand());
        result.setModel(data.getModel());
        result.setEngine(data.getEngine());
        result.setMakeYear(data.getMakeYear());
        return result;
    }
}
