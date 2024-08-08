package com.anas.booky.api.booky.patron;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PatronConfig {
    @Bean
    CommandLineRunner patronCommandLineRunner(final PatronRepository repository) {
        return args -> repository.saveAll(List.of(
                Patron.builder()
                        .firstName("Anas")
                        .lastName("Elgarhy")
                        .email("anas@email.com")
                        .phoneNumber("1234567890")
                        .address("123 Street")
                        .build(),
                Patron.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@email.com")
                        .phoneNumber("0987654321")
                        .address("456 Avenue")
                        .build()
        ));
    }
}