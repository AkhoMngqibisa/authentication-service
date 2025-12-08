package com.akhona.authentication.dto.request;

import lombok.*;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
}
