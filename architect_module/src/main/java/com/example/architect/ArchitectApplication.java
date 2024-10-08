package com.example.architect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ArchitectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectApplication.class, args);
    }

}
