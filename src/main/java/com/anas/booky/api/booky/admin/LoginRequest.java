package com.anas.booky.api.booky.admin;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}