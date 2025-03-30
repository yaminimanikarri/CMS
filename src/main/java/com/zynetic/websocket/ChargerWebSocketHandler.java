package com.zynetic.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zynetic.repository.ChargerRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
public class ChargerWebSocketHandler implements WebSocketHandler {
    private final ChargerRepository chargerRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChargerWebSocketHandler(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .map(msg -> msg.getPayloadAsText())
                .flatMap(this::processMessage)
                .then();
    }

    private Mono<Void> processMessage(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String action = jsonNode.get("action").asText();
            String chargerId = jsonNode.get("chargerId").asText();

            switch (action) {
                case "BootNotification":
                    return handleBootNotification(chargerId);
                case "Heartbeat":
                    return handleHeartbeat(chargerId);
                case "StatusNotification":
                    return handleStatusNotification(chargerId, jsonNode.get("status").asText());
                default:
                    return Mono.empty();
            }
        } catch (Exception e) {
            return Mono.empty();
        }
    }

    private Mono<Void> handleBootNotification(String chargerId) {
        return chargerRepository.findById(chargerId)
                .flatMap(charger -> {
                    charger.setStatus("Booted");
                    charger.setLastHeartbeat(Instant.now());
                    return chargerRepository.save(charger);
                }).then();
    }

    private Mono<Void> handleHeartbeat(String chargerId) {
        return chargerRepository.findById(chargerId)
                .flatMap(charger -> {
                    charger.setLastHeartbeat(Instant.now());
                    return chargerRepository.save(charger);
                }).then();
    }

    private Mono<Void> handleStatusNotification(String chargerId, String status) {
        return chargerRepository.findById(chargerId)
                .flatMap(charger -> {
                    charger.setStatus(status);
                    return chargerRepository.save(charger);
                }).then();
    }
}
