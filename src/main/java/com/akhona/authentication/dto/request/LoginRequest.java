package com.akhona.authentication.dto.request;

import lombok.*;

@Getter
@Setter
public class LoginRequest {

    private String email;
    private String password;

}
