package com.eunho.bankingsystem_spring_v2.repository;

import com.eunho.bankingsystem_spring_v2.domain.Bkpic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BkpicRepository extends JpaRepository<Bkpic, String> {
}
