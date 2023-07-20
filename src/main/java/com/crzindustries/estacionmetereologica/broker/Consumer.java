package com.crzindustries.estacionmetereologica.broker;

import com.crzindustries.estacionmetereologica.dtos.requests.NewDataRequest;
import com.crzindustries.estacionmetereologica.services.interfaces.IDataService;
import com.crzindustries.estacionmetereologica.websocket.services.SocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    @Autowired
    private IDataService dataService;

    @Autowired
    private SocketService socketService;

    @RabbitListener(queues = {"meteorologicalSensorsData"})
    public void onNewData(List<NewDataRequest> requestList) {
        dataService.saveAll(requestList);
        socketService.sendDataToSocket("data", "newData", requestList, "New data received", true);
    }
}
