package com.zynetic.service.impl;

import com.zynetic.model.Transaction;
import com.zynetic.repository.TransactionRepository;
import com.zynetic.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private TransactionRepository repository;

    @Override
    public Flux<Transaction> findByChargerId(String id) {
        return repository.findByChargerId(id);
    }

    @Override
    public Flux<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Mono<Transaction> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Transaction> findByChargerIdAndStartTimeBetween(String chargerId, Instant start, Instant end) {
        return repository.findByChargerIdAndStartTimeBetween(chargerId, start, end);
    }
}
