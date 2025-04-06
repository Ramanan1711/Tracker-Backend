package com.example.controller;


import com.example.dto.AdminRegisterRequest;
import com.example.dto.UserRegisterRequest;
import com.example.dto.UserResponse;
import com.example.dto.LoginRequest;
import com.example.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/register/admin")
//    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegisterRequest request) {
//        authService.registerAdmin(request);
//        return ResponseEntity.ok("Admin registered with business");
//    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody AdminRegisterRequest request) {
        UserResponse response = authService.registerAdmin(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest request) {
        UserResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        UserResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
