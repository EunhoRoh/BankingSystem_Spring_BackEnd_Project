package com.eunho.bankingsystem_spring_v2.repository;

import com.eunho.bankingsystem_spring_v2.domain.Bkuser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BkuserRepository extends JpaRepository<Bkuser, String> {
    Bkuser findByUsername(String username);
    @EntityGraph(attributePaths = {"bkuserRoleType.roleType"})
    Optional<Bkuser> findEntityGraphRoleTypeById(String id);
}
