package com.crzindustries.estacionmetereologica.websocket;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataModule {
    @Autowired
    private SocketIOServer server;

    @PostConstruct
    private void init() {
        SocketIONamespace monitorNamespace = server.addNamespace("data");
        monitorNamespace.addConnectListener(onConnected());
        monitorNamespace.addDisconnectListener(onDisconnected());
    }

    private ConnectListener onConnected() {
        return (client) -> {
            System.out.println("Client connected");
        };
    }

    private DisconnectListener onDisconnected() {
        return (client) -> {
            System.out.println("Client disconnected");
        };
    }
}
