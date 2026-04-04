package com.example.BookManagementApp.Entity;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
}