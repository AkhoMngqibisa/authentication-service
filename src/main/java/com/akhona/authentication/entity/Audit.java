package com.akhona.authentication.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private boolean success;
    private String ipAddress;
    private Instant timestamp;
}
