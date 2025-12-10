package com.akhona.authentication.repository;

import com.akhona.authentication.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
