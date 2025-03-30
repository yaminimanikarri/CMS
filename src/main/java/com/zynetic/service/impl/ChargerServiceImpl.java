package com.zynetic.service.impl;

import com.zynetic.model.Charger;
import com.zynetic.repository.ChargerRepository;
import com.zynetic.service.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChargerServiceImpl implements ChargerService {


    @Autowired
    private ChargerRepository repository;


    @Override
    public Mono<Charger> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Charger> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Charger> save(Charger charger) {
        return repository.save(charger);
    }
}
