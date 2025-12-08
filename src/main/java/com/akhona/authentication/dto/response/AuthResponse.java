package com.akhona.authentication.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String email;
    private String role;

    private String accessToken;
    private String refreshToken;

    private String tokenType;

    private long expiresIn;
}
