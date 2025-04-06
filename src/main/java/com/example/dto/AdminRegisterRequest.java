package com.example.dto;

import lombok.Data;

@Data
public class AdminRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String businessName;
    private String location;
}
