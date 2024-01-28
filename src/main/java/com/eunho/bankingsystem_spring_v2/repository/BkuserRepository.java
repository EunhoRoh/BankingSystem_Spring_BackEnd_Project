package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbuser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbuserRepository extends JpaRepository<Tbuser, String> {
    Tbuser findByUsername(String username);
    @EntityGraph(attributePaths = {"tbuserRoleType.roleType"})
    Optional<Tbuser> findEntityGraphRoleTypeById(String id);
}
