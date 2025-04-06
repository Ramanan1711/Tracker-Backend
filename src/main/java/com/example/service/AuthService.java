package com.example.service;

import com.example.dto.AdminRegisterRequest;
import com.example.dto.LoginRequest;
import com.example.dto.UserRegisterRequest;
import com.example.dto.UserResponse;

public interface AuthService {
    UserResponse registerAdmin(AdminRegisterRequest request);
    UserResponse registerUser(UserRegisterRequest request); // Changed return type from void to UserResponse
    UserResponse login(LoginRequest request);
}