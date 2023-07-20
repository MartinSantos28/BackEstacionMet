package com.crzindustries.estacionmetereologica.dtos.requests;

import lombok.Getter;

@Getter
public class NewDataRequest {
    private String sensorType;
    private String sensorValue;
    private String sensorMeasurement;
}
