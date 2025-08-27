package com.mitrais.vehicle_service_system.controller;

import com.mitrais.vehicle_service_system.dto.BaseVehicleRequest;
import com.mitrais.vehicle_service_system.entity.Vehicle;
import com.mitrais.vehicle_service_system.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
@Tag(name = "Vehicle API", description = "Vehicle related endpoint")
public class VehicleController {
    private final VehicleService service;

    @Operation(summary = "Create Vehicle API", description = "Create Vehicle based on request detail")
    @PostMapping("")
    public ResponseEntity<Void> create(@Valid @RequestBody BaseVehicleRequest createRequest) {
        service.create(createRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update Vehicle API", description = "Create Vehicle based on request detail")
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@Valid Long id, @Valid @RequestBody BaseVehicleRequest updateRequest) {
        Vehicle updatedEntity = service.update(id, updateRequest);
        return ResponseEntity.ok(updatedEntity);
    }

    @Operation(summary = "Search Vehicle API", description = "Search Vehicle based on brand, model, engine")
    @GetMapping("")
    public ResponseEntity<List<Vehicle>> findVehicleBy(@RequestParam(required = true) String brand,
                                                       @RequestParam(required = true) String model,
                                                       @RequestParam(required = true) String engine) {
        List<Vehicle> result = service.findVehicleFrom(brand, model, engine);
        return ResponseEntity.ok(result);
    }
}
