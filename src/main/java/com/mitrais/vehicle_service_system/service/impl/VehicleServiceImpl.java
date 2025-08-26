package com.mitrais.vehicle_service_system.service.impl;

import com.mitrais.vehicle_service_system.dto.BaseVehicleRequest;
import com.mitrais.vehicle_service_system.entity.Vehicle;
import com.mitrais.vehicle_service_system.mapper.VehicleMapper;
import com.mitrais.vehicle_service_system.repository.VehicleRepository;
import com.mitrais.vehicle_service_system.service.VehicleService;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository repository;

    @Override
    public void create(BaseVehicleRequest createRequest) {
        Vehicle newEntity = VehicleMapper.toEntity(createRequest);
        repository.save(newEntity);
    }

    @Override
    public Vehicle update(@Nonnull Long id, BaseVehicleRequest updateRequest) throws EntityNotFoundException {
        Vehicle existingVehicle = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle with id : " + id + "not found"));
        Vehicle updatedVehicle = VehicleMapper.toEntity(updateRequest);
        updatedVehicle.setId(existingVehicle.getId());
        updatedVehicle.setOperations(existingVehicle.getOperations());
        updatedVehicle = repository.save(updatedVehicle);
        return updatedVehicle;
    }

    @Override
    public List<Vehicle> findVehicleFrom(String brand, String model, String engine) {
        List<Vehicle> result = repository.findByModelAndBrandAndEngine(brand, model, engine);
        return result;
    }
}