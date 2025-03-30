package com.zynetic.repository;

import com.zynetic.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.Instant;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByChargerId(String chargerId);

    Flux<Transaction> findByChargerIdAndStartTimeBetween(String chargerId, Instant start, Instant end);

}
