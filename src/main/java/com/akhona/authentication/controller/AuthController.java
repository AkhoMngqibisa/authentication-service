package com.akhona.authentication.controller;

import com.akhona.authentication.dto.request.*;
import com.akhona.authentication.dto.response.*;
import com.akhona.authentication.entity.*;
import com.akhona.authentication.security.CustomUserDetails;
import com.akhona.authentication.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Auth APIs")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @Operation(summary = "User login")
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Login Successful"),
           @ApiResponse(responseCode = "401", description = "Invalid Credentials")
    })
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpRequest) {
        return authService.login(loginRequest,httpRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<UserProfileResponse> user (Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        UserProfileResponse response = UserProfileResponse.builder()
                .userId(customUserDetails.getId())
                .email(customUserDetails.getUsername())
                .role(customUserDetails.getRole().name())
                .build();

        return ResponseEntity.ok(response);
    }
}
