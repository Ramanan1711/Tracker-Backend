package com.example.service.impl;

import com.example.dto.AdminRegisterRequest;
import com.example.dto.UserRegisterRequest;
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
    public void registerAdmin(AdminRegisterRequest request) {
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

        userRepository.save(user);
    }

    @Override
    public void registerUser(UserRegisterRequest request) {
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

        userRepository.save(user);
    }
}
