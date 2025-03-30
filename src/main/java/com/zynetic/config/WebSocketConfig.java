package com.zynetic.config;


import com.zynetic.websocket.ChargerWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;

import java.util.Collections;

@Configuration
public class WebSocketConfig {

    private final ChargerWebSocketHandler chargerWebSocketHandler;

    public WebSocketConfig(ChargerWebSocketHandler chargerWebSocketHandler) {
        this.chargerWebSocketHandler = chargerWebSocketHandler;
    }

    @Bean
    public SimpleUrlHandlerMapping webSocketMapping() {
        return new SimpleUrlHandlerMapping(Collections.singletonMap("/ocpp", chargerWebSocketHandler), 1);
    }

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter(WebSocketService webSocketService) {
        return new WebSocketHandlerAdapter(webSocketService);
    }

    @Bean
    public WebSocketService webSocketService() {
        return new HandshakeWebSocketService();
    }
}
