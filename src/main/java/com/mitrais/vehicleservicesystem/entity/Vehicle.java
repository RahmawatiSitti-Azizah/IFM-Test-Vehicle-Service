package com.mitrais.vehicleservicesystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Long id;
    private String brand;
    private String model;
    private String engine;
    private Integer makeYear;
    @OneToMany(cascade = {CascadeType.DETACH})
    @JoinColumns({
            @JoinColumn(name = "brand", referencedColumnName = "brand", insertable = false, updatable = false),
            @JoinColumn(name = "model", referencedColumnName = "model", insertable = false, updatable = false),
            @JoinColumn(name = "engine", referencedColumnName = "engine", insertable = false, updatable = false)
    })
    private List<Operation> operations;
}
