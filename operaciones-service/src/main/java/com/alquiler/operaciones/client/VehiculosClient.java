package com.alquiler.operaciones.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class VehiculosClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${vehiculos.service.url}")
    private String vehiculosServiceUrl;

    public Map getVehiculo(Long vehiculoId) {
        return webClientBuilder.build()
                .get()
                .uri(vehiculosServiceUrl + "/vehiculos/" + vehiculoId)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

}
