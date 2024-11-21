package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링 설정 클래스임을 지정
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig {

    @Bean // 명시적으로 Bean을 등록하여 의존성을 주입
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // SecurityFilterChain을 반환하며 현재는 별도 설정 없이 기본값 사용
        return http.build();
    }

    @Bean // 비밀번호 암호화를 위한 Bean 등록
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder를 사용해 비밀번호를 암호화
        return new BCryptPasswordEncoder();
    }
}
