package com.akhona.authentication.controller;

import com.akhona.authentication.dto.request.*;
import com.akhona.authentication.dto.response.*;
import com.akhona.authentication.entity.*;
import com.akhona.authentication.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<UserProfileResponse> user (Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserProfileResponse response = UserProfileResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();

        return ResponseEntity.ok(response);
    }
}
