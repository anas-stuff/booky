package com.anas.booky.api.booky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BookyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookyApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Booky";
    }

}
