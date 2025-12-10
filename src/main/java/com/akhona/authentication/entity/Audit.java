package com.akhona.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "login_audit",
       indexes = {
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_timestamp", columnList = "timestamp")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private boolean success;
    private String ipAddress;
    private String userAgent;
    private String failureReason;
    private Instant timestamp;
}
