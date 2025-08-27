package com.mitrais.vehicle_service_system.controller;

import com.mitrais.vehicle_service_system.dto.BaseOperationRequest;
import com.mitrais.vehicle_service_system.entity.Operation;
import com.mitrais.vehicle_service_system.service.OperationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
@Tag(name = "Operation API", description = "Operation related endpoint")
public class OperationController {
    private final OperationService service;

    @PostMapping("")
    private ResponseEntity<Void> create(@Valid @RequestBody BaseOperationRequest createRequest) {
        service.create(createRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Operation> update(@PathVariable Long id, @Valid @RequestBody BaseOperationRequest updateRequest) {
        Operation operation = service.update(id, updateRequest);
        return ResponseEntity.ok(operation);
    }

    @GetMapping("")
    private ResponseEntity<List<Operation>> search(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String engine,
            @RequestParam(required = false) Integer yearStart,
            @RequestParam(required = false) Integer yearEnd,
            @RequestParam(required = false) Double distanceStart,
            @RequestParam(required = false) Double distanceEnd) {
        List<Operation> result = service.search(brand, model, engine, yearStart, yearEnd, distanceStart, distanceEnd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    private ResponseEntity<List<Operation>> searchByVehicle(
            @RequestParam(required = true) String brand,
            @RequestParam(required = true) String model,
            @RequestParam(required = true) String engine,
            @RequestParam(required = true) Integer makeYear,
            @RequestParam(required = true) Double totalDistance,
            @RequestParam(required = false, defaultValue = "km") String unit) {
        List<Operation> result = service.searchByVehicle(brand, model, engine, makeYear, totalDistance, unit);
        return ResponseEntity.ok(result);
    }
}
