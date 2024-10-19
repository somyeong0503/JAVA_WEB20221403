package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity // TestDB 객체와 DB 테이블을 매핑. JPA가 관리
@Table(name = "testdb") // 테이블 이름은 testdb
@Data // Lombok: getter/setter/toString 등을 자동 생성
public class TestDB {

    @Id // 해당 필드를 PK로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (값이 없으면 자동 할당)
    private Long id;

    @Column(nullable = true) // 테이블의 컬럼으로 설정. null 허용
    private String name;
}
