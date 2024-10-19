package com.example.demo.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.domain.TestDB;

@Repository
public interface TestRepository extends JpaRepository<TestDB, Long> {
    
    // 이름으로 TestDB 엔티티를 조회하는 메서드
    TestDB findByName(String name);
    // 모든 데이터 조회
    List<TestDB> findAll();
}
