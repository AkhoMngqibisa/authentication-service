package com.akhona.authentication.service;

import com.akhona.authentication.entity.User;
import com.akhona.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AccountSecurityService {

    private final UserRepository userRepository;

    private static final int MAX_ATTEMPTS = 5;
    private static final Duration LOCK_DURATION = Duration.ofMinutes(15);

    public void recordFailedAttempt(User user) {

        int attempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(attempts);

        if (attempts >= MAX_ATTEMPTS) {
            user.setAccountLocked(true);
            user.setLockTime(Instant.now());
        }

        userRepository.save(user);
    }

    public void resetAttempts(User user) {
        user.setFailedAttempts(0);
        user.setAccountLocked(false);
        user.setLockTime(null);
        userRepository.save(user);
    }

    public void checkLockStatus(User user) {

        if (!user.isAccountLocked()) return;

        if (user.getLockTime()
                .plus(LOCK_DURATION)
                .isBefore(Instant.now())) {

            resetAttempts(user);
        } else {
            throw new RuntimeException("Account is locked. Try again later.");
        }
    }
}