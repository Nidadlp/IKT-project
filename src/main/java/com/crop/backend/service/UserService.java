package com.crop.backend.service;

import com.crop.backend.dto.RegisterRequest;
import com.crop.backend.model.User;
import com.crop.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request){

        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("User already exists!");

        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .additionalInfo(request.getAdditionalInfo())
                .build();

        return userRepository.save(user);
    }


}
