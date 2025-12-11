package com.akhona.authentication.service;

import com.akhona.authentication.entity.Audit;
import com.akhona.authentication.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditRepository auditRepository;

    public void logSuccess(String email, String ip, String userAgent) {
        save(email, true, ip, userAgent, null);
    }

    public void logFailure(String email, String ip, String userAgent, String reason) {
        save(email, false, ip, userAgent, reason);
    }

    private void save(String email, boolean success, String ip, String userAgent, String reason) {

        Audit audit = Audit.builder()
                .email(email)
                .success(success)
                .ipAddress(ip)
                .userAgent(userAgent)
                .failureReason(reason)
                .createdAt(Instant.now())
                .build();

        auditRepository.save(audit);
    }

}
