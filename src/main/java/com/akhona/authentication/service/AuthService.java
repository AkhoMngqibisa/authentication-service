package com.akhona.authentication.service;

import com.akhona.authentication.dto.request.LoginRequest;
import com.akhona.authentication.dto.request.RegisterRequest;
import com.akhona.authentication.dto.response.AuthResponse;
import com.akhona.authentication.entity.*;
import com.akhona.authentication.repository.UserRepository;
import com.akhona.authentication.util.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private AuditService auditService;

    @Autowired
    private AccountSecurityService accountSecurityService;

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

    public AuthResponse login(LoginRequest loginRequest, HttpServletRequest httpRequest) {
        String ip = RequestUtil.getClientIpAddress(httpRequest);
        String agent = RequestUtil.getUserAgent(httpRequest);
        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    auditService.logFailure(loginRequest.getEmail(), ip, agent,"User not found");
                    return new RuntimeException("User not found");
                });

        accountSecurityService.checkLockStatus(user);

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            accountSecurityService.recordFailedAttempt(user);
            auditService.logFailure(user.getEmail(), ip, agent, "Invalid password");
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtService.generateToken(user.getEmail());
        accountSecurityService.resetAttempts(user);
        auditService.logSuccess(user.getEmail(), ip, agent);

        return AuthResponse.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(86400000)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

}
