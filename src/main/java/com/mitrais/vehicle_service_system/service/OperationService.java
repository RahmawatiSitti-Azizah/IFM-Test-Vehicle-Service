package com.mitrais.vehicle_service_system.service;

import com.mitrais.vehicle_service_system.dto.BaseOperationRequest;
import com.mitrais.vehicle_service_system.entity.Operation;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

public interface OperationService {

    /**
     * Create new Operation
     * @param createRequest operation details to create
     */
    void create(@Valid BaseOperationRequest createRequest);

    /**
     * Update Operation for specific id.
     * @param id Operation id to be updated
     * @param updateRequest operation details to update
     * @return updated operation entity
     */
    Operation update(@Nonnull Long id, @Valid BaseOperationRequest updateRequest) throws EntityNotFoundException;

    /**
     * Search operation based on (brand/model/engine/yearStart/yearEnd/distanceStart/distanceEnd)
     * with distance unit setting (km/miles)
     * @param brand keyword for brand to be included in filter
     * @param model keyword for model to be included in filter
     * @param engine keyword for engine to be included in filter
     * @param yearStart keyword for yearStart to be included in filter
     * @param yearEnd keyword for yearEnd to be included in filter
     * @param distanceStart keyword for distanceStart to be included in filter
     * @param distanceEnd keyword for distanceEnd to be included in filter
     * @param unit mode of distance can be set to (km/miles)
     * @return List of operation based on filter sort by approxCost in ascending order (can be empty but not null)
     */
    List<Operation> search(String brand, String model, String engine, Integer yearStart, Integer yearEnd, Double distanceStart, Double distanceEnd, String unit);
}
