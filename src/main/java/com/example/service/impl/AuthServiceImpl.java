package com.example.service.impl;

import com.example.dto.AdminRegisterRequest;
import com.example.dto.UserRegisterRequest;
import com.example.dto.UserResponse;
import com.example.dto.LoginRequest;
import com.example.entity.Business;
import com.example.entity.User;
import com.example.repository.BusinessRepository;
import com.example.repository.UserRepository;
import com.example.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

//    public AuthServiceImpl(BusinessRepository businessRepository, UserRepository userRepository) {
//        this.businessRepository = businessRepository;
//        this.userRepository = userRepository;
//    }

    @Override
    public UserResponse registerAdmin(AdminRegisterRequest request) {
        Business business = Business.builder()
                .businessName(request.getBusinessName())
                .build();
        business = businessRepository.save(business);

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword()) // ⚠️ Hash in production
                .role("ADMIN")
                .location(request.getLocation())
                .business(business)
                .build();

//        userRepository.save(user);
        user = userRepository.save(user);

        return UserResponse.builder()
//                .userId(user.getUserId())
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .location(user.getLocation())
                .businessId(business.getBusinessId())
                .businessName(business.getBusinessName())
                .build();
    }

    @Override
    public UserResponse registerUser(UserRegisterRequest request) {
        Business business = businessRepository.findById(request.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .location(request.getLocation())
                .business(business)
                .build();

        user = userRepository.save(user);

        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .location(user.getLocation())
                .businessId(business.getBusinessId())
                .businessName(business.getBusinessName())
                .build();
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // In production, you should use proper password encryption/comparison
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .location(user.getLocation())
                .businessId(user.getBusiness().getBusinessId())
                .businessName(user.getBusiness().getBusinessName())
                .build();
    }
}
