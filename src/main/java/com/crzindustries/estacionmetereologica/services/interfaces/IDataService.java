package com.crzindustries.estacionmetereologica.services.interfaces;

import com.crzindustries.estacionmetereologica.dtos.requests.BaseResponse;
import com.crzindustries.estacionmetereologica.dtos.requests.NewDataRequest;

import java.util.List;

public interface IDataService {
    BaseResponse saveAll(List<NewDataRequest> list);
    BaseResponse getStatistics(String sensorType);
    BaseResponse list();
}
