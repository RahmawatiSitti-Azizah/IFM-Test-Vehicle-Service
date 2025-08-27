package com.mitrais.vehicleservicesystem.service.impl;

import com.mitrais.vehicleservicesystem.dto.BaseVehicleRequest;
import com.mitrais.vehicleservicesystem.entity.Vehicle;
import com.mitrais.vehicleservicesystem.mapper.VehicleMapper;
import com.mitrais.vehicleservicesystem.repository.VehicleRepository;
import com.mitrais.vehicleservicesystem.service.VehicleService;
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
        List<Vehicle> result = repository.findByModelAndBrandAndEngine(model, brand, engine);
        return result;
    }
}