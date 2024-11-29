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

    @Bean // SecurityFilterChain을 반환하여 HTTP 보안 설정
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // XSS 보호 헤더 설정
            .headers(headers -> headers
                .addHeaderWriter((request, response) -> {
                    response.setHeader("X-XSS-Protection", "1; mode=block"); // XSS-Protection 헤더 추가
                })
            )
            
            // CSRF 보호 설정 (CSRF 기본 사용)
            .csrf(csrf -> csrf
                .csrfTokenRepository(org.springframework.security.web.csrf.CookieCsrfTokenRepository.withHttpOnlyFalse()) // CSRF 토큰을 쿠키에 저장
            )
            
            // 세션 관리 설정
            .sessionManagement(session -> session
                .invalidSessionUrl("/session-expired") // 세션 만료 시 이동할 URL 설정
                .maximumSessions(1) // 사용자 별 최대 세션 수
                .maxSessionsPreventsLogin(true) // 동시 세션 제한
            );
        
        return http.build(); // 보안 설정을 반환
    }

    @Bean // 비밀번호 암호화를 위한 Bean 등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder를 사용해 비밀번호 암호화
    }
}
