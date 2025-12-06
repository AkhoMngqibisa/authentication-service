package com.akhona.authentication.dto;

import lombok.*;

@Getter
@Setter
public class LoginRequest {

    private String email;
    private String password;

}
