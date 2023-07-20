package com.crzindustries.estacionmetereologica.services;

import com.crzindustries.estacionmetereologica.dtos.requests.BaseResponse;
import com.crzindustries.estacionmetereologica.dtos.requests.NewDataRequest;
import com.crzindustries.estacionmetereologica.entities.Data;
import com.crzindustries.estacionmetereologica.repositories.IDataRepository;
import com.crzindustries.estacionmetereologica.services.interfaces.IDataService;
import com.crzindustries.estacionmetereologica.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements IDataService {

    @Autowired
    private IDataRepository repository;

    @Override
    public BaseResponse saveAll(List<NewDataRequest> requestList) {
        List<Data> dataList = requestList.stream().map(this::from).toList();

        List<Data> savedData = repository.saveAll(dataList);

        return BaseResponse.builder()
                .data(savedData)
                .success(true)
                .message("Data saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public BaseResponse getStatistics(String sensorType) {
        if (sensorType == null || sensorType.isEmpty()) {
            return BaseResponse.builder()
                    .data(null)
                    .success(false)
                    .message("Sensor type is required")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }

        List<Data> dataList = repository.findAllBySensorType(sensorType);

        Float average = StatisticsUtils.getAverage(dataList.stream().map(data -> Float.parseFloat(data.getSensorValue())).toList());
        Float median = StatisticsUtils.getMedian(dataList.stream().map(data -> Float.parseFloat(data.getSensorValue())).toList());
        Float mode = StatisticsUtils.getMode(dataList.stream().map(data -> Float.parseFloat(data.getSensorValue())).toList());

        Map<String, Float> statistics = Map.of(
                "average", average,
                "median", median,
                "mode", mode
        );

        return BaseResponse.builder()
                .data(statistics)
                .success(true)
                .message("Data retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse list() {
        List<Data> dataList = repository.findAll();

        return BaseResponse.builder()
                .data(dataList)
                .success(true)
                .message("Data retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private Data from(NewDataRequest request) {
        Data data = new Data();

        data.setSensorType(request.getSensorType());
        data.setSensorValue(request.getSensorValue());
        data.setSensorMeasurement(request.getSensorMeasurement());

        return data;
    }

}
