package com.zynetic.controller;


import com.zynetic.model.Charger;
import com.zynetic.service.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ChargerController {

    @Autowired
    private ChargerService chargerService;


    //    @Operation(summary = "Get all chargers")
    @GetMapping(value = "/charger")
    public Flux<Charger> getAllChargers() {
        return chargerService.findAll();
    }

    //    @Operation(summary = "Add a new charger")
    @PostMapping(value = "/charger")
    public Mono<Charger> addCharger(@RequestBody Charger charger) {
        return chargerService.save(charger);
    }
}
