package com.mitrais.vehicle_service_system.service.impl;

import com.mitrais.vehicle_service_system.dto.BaseOperationRequest;
import com.mitrais.vehicle_service_system.entity.Operation;
import com.mitrais.vehicle_service_system.mapper.OperationMapper;
import com.mitrais.vehicle_service_system.repository.OperationRepository;
import com.mitrais.vehicle_service_system.repository.spec.OperationSpecs;
import com.mitrais.vehicle_service_system.service.OperationService;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository repository;

    public OperationServiceImpl(OperationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(BaseOperationRequest createRequest) {
        Operation operation = OperationMapper.convert(createRequest);
        repository.save(operation);
    }

    @Override
    public Operation update(@Nonnull Long id, BaseOperationRequest updateRequest) throws EntityNotFoundException {
        Operation existingEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Operation with id " + id + " not found"));
        Operation updatedEntity = OperationMapper.convert(updateRequest);
        updatedEntity.setId(existingEntity.getId());
        updatedEntity = repository.save(updatedEntity);
        return updatedEntity;
    }

    @Override
    public List<Operation> search(String brand, String model, String engine, Integer yearStart, Integer yearEnd, Double distanceStart, Double distanceEnd) {
        if (StringUtils.hasText(brand) || StringUtils.hasText(model) || StringUtils.hasText(engine) || yearStart != null || yearEnd != null || distanceStart != null || distanceEnd != null) {
            Specification<Operation> spec = Specification.anyOf(
                    OperationSpecs.hasFieldLike("brand", brand),
                    OperationSpecs.hasFieldLike("model", model),
                    OperationSpecs.hasFieldLike("engine", engine),
                    OperationSpecs.hasFieldEqual("yearStart", yearStart),
                    OperationSpecs.hasFieldEqual("yearEnd", yearEnd),
                    OperationSpecs.hasFieldEqual("distanceStart", distanceStart),
                    OperationSpecs.hasFieldEqual("distanceEnd", yearStart));
            return repository.findAll(spec);
        }
        return repository.findAll();
    }

    @Override
    public List<Operation> searchByVehicle(String brand, String model, String engine, Integer makeYear, Double totalDistance, String unit) {
        return repository.findAllBy(brand, model, engine, makeYear, totalDistance);
    }
}
