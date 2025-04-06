package com.example.service;

import com.example.dto.AdminRegisterRequest;
import com.example.dto.UserRegisterRequest;

public interface AuthService {
    void registerAdmin(AdminRegisterRequest request);
    void registerUser(UserRegisterRequest request);
}