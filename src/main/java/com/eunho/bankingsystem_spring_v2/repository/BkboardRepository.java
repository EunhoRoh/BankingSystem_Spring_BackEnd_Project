package com.eunho.bankingsystem_spring_v2.repository;

import com.eunho.bankingsystem_spring_v2.domain.Bkboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BkboardRepository extends JpaRepository<Bkboard, String> {
    /*
    BkboardRepository를 사용하기 위해,
    JpaRepository<Bkboard, String>에서
    Bkboard 라는 엔티티 클래스를 먼저 제공하고, Bkboard의 인덱스pk의 자료형을 선언해줍니다.

    기존에 이미 구현된 JPA 기능을 모두 사용 가능!!
    */
}
