package com.zynetic.controller;

import com.zynetic.model.Transaction;
import com.zynetic.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Flux<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{chargerId}")
    public Flux<Transaction> getTransactionsByCharger(@PathVariable String chargerId) {
        return transactionService.findByChargerId(chargerId);
    }

    @PostMapping
    public Mono<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @PostMapping("/start")
    public Mono<Transaction> startTransaction(@RequestBody Transaction transaction) {
        transaction.setStartTime(Instant.now()); // Set start time to now
        return transactionService.save(transaction);
    }

    @PutMapping("/end/{id}")
    public Mono<Transaction> endTransaction(@PathVariable String id, @RequestParam double energyConsumed) {
        return transactionService.findById(id)
                .flatMap(existingTransaction -> {
                    existingTransaction.setEndTime(Instant.now()); // Set end time to now
                    existingTransaction.setEnergyConsumed(energyConsumed);
                    return transactionService.save(existingTransaction);
                });
    }

    @GetMapping("/history")
    public Flux<Transaction> getTransactionHistory(String chargerId, Instant start, Instant end) {
        return transactionService.findByChargerIdAndStartTimeBetween(chargerId, start, end);
    }

}
