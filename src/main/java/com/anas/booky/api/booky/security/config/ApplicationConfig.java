package com.anas.booky.api.booky.security.config;

import com.anas.booky.api.booky.admin.AdminRepository;
import com.anas.booky.api.booky.admin.exceptions.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AdminRepository adminRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> adminRepository.findByEmail(username).orElseThrow(() -> new AdminNotFoundException(username));
    }
}
