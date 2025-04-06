package com.example.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private UUID businessId;
    private String role; // MANAGER or SALESPERSON
    private String location;
}
