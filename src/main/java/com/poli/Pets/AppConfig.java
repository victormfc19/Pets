package com.poli.Pets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    public static final String REST_API_PETS = "http://localhost:8080/api/v1/pets";
    public static final String REST_API_CLIENTS = "http://localhost:8080/api/v1/clients";
    public static final String REST_API_MEDICINES = "http://localhost:8080/api/v1/medicines";
    public static final String REST_API_REPORT = "http://localhost:8080/api/v1/report";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
