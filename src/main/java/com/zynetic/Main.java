package com.zynetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.zynetic")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
}