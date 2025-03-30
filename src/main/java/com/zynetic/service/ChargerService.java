package com.zynetic.service;


import com.zynetic.model.Charger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChargerService {

    Mono<Charger> findById(String id);

    Flux<Charger> findAll();

    Mono<Charger> save(Charger charger);

}
