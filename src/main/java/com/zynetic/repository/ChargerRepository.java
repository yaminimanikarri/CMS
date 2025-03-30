package com.zynetic.repository;


import com.zynetic.model.Charger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface ChargerRepository extends ReactiveMongoRepository<Charger, String> {
    Mono<Charger> findById(String id);

    Mono<Charger> save(Charger charger);
}
