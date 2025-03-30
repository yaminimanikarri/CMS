package com.zynetic.service;


import com.zynetic.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface TransactionService {

    Flux<Transaction> findByChargerId(String id);

    Flux<Transaction> findAll();

    Mono<Transaction> save(Transaction charger);

    Mono<Transaction> findById(String id);

    Flux<Transaction> findByChargerIdAndStartTimeBetween(String chargerId, Instant start, Instant end);
}
