package com.example.controller;


import com.example.dto.AdminRegisterRequest;
import com.example.dto.UserRegisterRequest;
import com.example.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegisterRequest request) {
        authService.registerAdmin(request);
        return ResponseEntity.ok("Admin registered with business");
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok("User registered under business");
    }
}
