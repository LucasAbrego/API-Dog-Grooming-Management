package com.zero.dogGrooming;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DogGroomingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogGroomingApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Dog Grooming API")
                .version("1.0")
                .description("REST API for managing clients, pets, and appointments at a dog grooming salon, built with Spring Boot, JPA (Hibernate), and MySQL."));
    }
}
