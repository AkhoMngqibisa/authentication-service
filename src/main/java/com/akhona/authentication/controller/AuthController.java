package com.akhona.authentication.controller;

import com.akhona.authentication.dto.request.LoginRequest;
import com.akhona.authentication.dto.request.RegisterRequest;
import com.akhona.authentication.dto.response.AuthResponse;
import com.akhona.authentication.service.*;
import org.springframework.beans.factory.annotation.*;
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
}
