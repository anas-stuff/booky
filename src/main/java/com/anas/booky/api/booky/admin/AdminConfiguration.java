package com.anas.booky.api.booky.admin;

import io.jsonwebtoken.io.Decoders;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class AdminConfiguration {
    @Bean
    CommandLineRunner createDefaultAdmins(final  AdminRepository adminRepository, final PasswordEncoder passwordEncoder) {
        return args -> adminRepository.saveAll(
                List.of(
                        Admin.builder()
                                .name("root")
                                .email("root@root.local")
                                .passwordHash(passwordEncoder.encode("root"))
                                .build()
                )
        );
    }
}
