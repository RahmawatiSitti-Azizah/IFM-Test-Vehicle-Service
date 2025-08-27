package com.mitrais.vehicleservicesystem.mapper;

import com.mitrais.vehicleservicesystem.dto.BaseOperationRequest;
import com.mitrais.vehicleservicesystem.entity.Operation;

public class OperationMapper {
    public static Operation convert(BaseOperationRequest data) {
        Operation operation = new Operation();
        operation.setName(data.getName());
        operation.setBrand(data.getBrand());
        operation.setModel(data.getModel());
        operation.setEngine(data.getEngine());
        operation.setYearStart(data.getYearStart());
        operation.setYearEnd(data.getYearEnd());
        operation.setDistanceStart(data.getDistanceStart());
        operation.setDistanceEnd(data.getDistanceEnd());
        operation.setApproxCost(data.getApproxCost());
        operation.setDescription(data.getDescription());
        operation.setTime(data.getTime());
        return operation;
    }
}
