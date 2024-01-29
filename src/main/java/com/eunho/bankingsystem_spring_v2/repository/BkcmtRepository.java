package com.eunho.bankingsystem_spring_v2.repository;

import com.eunho.bankingsystem_spring_v2.domain.Bkcmt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BkcmtRepository extends JpaRepository<Bkcmt, String> {
}
