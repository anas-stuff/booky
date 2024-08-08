package com.anas.booky.api.booky.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String cause;
    private final String message;
    private final LocalDateTime timestamp;
    private final String details;
}