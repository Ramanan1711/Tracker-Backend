package com.example.service;

import com.example.entity.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers(UUID businessId);
}