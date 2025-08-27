package com.mitrais.vehicleservicesystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Long id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String engine;
    @Column(nullable = false)
    private Integer yearStart;
    @Column(nullable = false)
    private Integer yearEnd;
    @Column(nullable = false)
    private Double distanceStart;
    @Column(nullable = false)
    private Double distanceEnd;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double approxCost;
    private String description;
    @Column(nullable = false)
    private Integer time;
}
