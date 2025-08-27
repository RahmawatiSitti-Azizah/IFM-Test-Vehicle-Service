package com.mitrais.vehicleservicesystem.service;

import com.mitrais.vehicleservicesystem.dto.BaseVehicleRequest;
import com.mitrais.vehicleservicesystem.entity.Vehicle;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

public interface VehicleService {

    /**
     * Create new Vehicle entity
     *
     * @param createRequest vehicle details to create
     */
    void create(@Valid BaseVehicleRequest createRequest);

    /**
     * Update vehicle for specific id.
     *
     * @param id            vehicle id to be updated
     * @param updateRequest vehicle details to update
     * @return updated vehicle entity
     */
    Vehicle update(@Nonnull Long id, @Valid BaseVehicleRequest updateRequest) throws EntityNotFoundException;

    /**
     * Find list of vehicle based on (brand, model and engine)
     *
     * @param brand  brand name ot the vehicle
     * @param model  model of the vehicle
     * @param engine enging of the vehicle
     * @return List of vehicle that match all the criteria above (can be empty list but not null)
     */
    List<Vehicle> findVehicleFrom(String brand, String model, String engine);
}
