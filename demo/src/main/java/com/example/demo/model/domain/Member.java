package com.example.demo.model.domain;// 패키지 구조에 맞게 수정 필요

import jakarta.persistence.*; // JPA 관련 어노테이션 import
import lombok.AccessLevel;
import lombok.Builder; // 빌더 패턴을 사용하기 위한 Lombok import
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // JPA 엔티티임을 명시
@Table(name = "member") // 테이블명 설정
@Getter // Lombok을 사용해 Getter 메서드 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성자 접근 방지

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키를 자동 증가
    @Column(name = "id", updatable = false) // 컬럼 설정, 수정 불가
    private Long id;

    @Column(name = "name", nullable = false) // 컬럼 설정, null 허용하지 않음
    private String name = "";

    @Column(name = "email", unique = true, nullable = false) // 유일성 보장, null 허용하지 않음
    private String email = "";

    @Column(name = "password", nullable = false) // null 허용하지 않음
    private String password = "";

    @Column(name = "age", nullable = false) // null 허용하지 않음
    private String age = "";

    @Column(name = "mobile", nullable = false) // null 허용하지 않음
    private String mobile = "";

    @Column(name = "address", nullable = false) // null 허용하지 않음
    private String address = "";

    @Builder // Builder 패턴을 사용해 객체 생성
    public Member(String name, String email, String password, String age, String mobile, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
    }
}
