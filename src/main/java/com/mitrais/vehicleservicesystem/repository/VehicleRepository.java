package com.mitrais.vehicleservicesystem.repository;

import com.mitrais.vehicleservicesystem.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByModelAndBrandAndEngine(String model, String brand, String engine);
}
