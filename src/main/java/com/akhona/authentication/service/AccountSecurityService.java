package com.akhona.authentication.service;

import com.akhona.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountSecurityService {

    private final UserRepository userRepository;

}