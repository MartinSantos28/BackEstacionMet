package com.crzindustries.estacionmetereologica.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "data")
@Getter @Setter
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorType;

    private String sensorValue;

    private String sensorMeasurement;
}
