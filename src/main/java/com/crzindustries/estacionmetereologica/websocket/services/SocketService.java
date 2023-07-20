package com.crzindustries.estacionmetereologica.websocket.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.crzindustries.estacionmetereologica.constants.MessageType;
import com.crzindustries.estacionmetereologica.websocket.dtos.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

    @Autowired
    private SocketIOServer socketIOServer;

    public void sendDataToSocket(String namespace, String event, Object data, String message, Boolean success) {
        Message messageObject = from(data, message, success);
        socketIOServer.getNamespace(namespace).getBroadcastOperations().sendEvent(event, messageObject);
    }

    public void sendErrorMessageToSocket(SocketIOClient client, String message) {
        Message messageObject = from(null, message, false);
        client.sendEvent("error", messageObject);
    }

    private Message from(Object data, String message, Boolean success) {
        return Message.builder()
                .data(data)
                .message(message)
                .success(success)
                .type(MessageType.SERVER)
                .build();
    }
}
