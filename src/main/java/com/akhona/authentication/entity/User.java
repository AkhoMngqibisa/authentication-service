package com.akhona.authentication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @Column(name = "failed_attempts")
    private int failedAttempts;

    @Column(name = "account_locked")
    private boolean accountLocked;
}
