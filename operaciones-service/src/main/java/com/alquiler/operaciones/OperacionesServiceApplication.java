package com.alquiler.operaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OperacionesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperacionesServiceApplication.class, args);
    }
}
