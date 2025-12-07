package com.akhona.authentication.service;

import com.akhona.authentication.dto.AuthResponse;
import com.akhona.authentication.dto.RegisterRequest;
import com.akhona.authentication.entity.*;
import com.akhona.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = new User();

        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(true);

        userRepository.save(user);

        String accessToken = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(86400000)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

}
