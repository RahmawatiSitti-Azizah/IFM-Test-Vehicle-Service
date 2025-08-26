package com.mitrais.vehicle_service_system.repository;

import com.mitrais.vehicle_service_system.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>, JpaSpecificationExecutor<Operation> {
    @Query("SELECT o FROM Operation o " +
            "WHERE o.brand = :brand AND o.model = :model AND o.engine = :engine " +
            "AND yearStart <= :makeYear AND yearEnd >= :makeYear " +
            "AND distanceStart <= :totalDistance AND distanceEnd >= : totalDistance")
    List<Operation> findAllBy(String brand, String model, String engine, Integer makeYear, Double totalDistance);
}
