package com.example.demo.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

import com.example.demo.model.domain.Member;
import com.example.demo.model.repository.MemberRepository;

import jakarta.validation.Valid;



import lombok.RequiredArgsConstructor;

@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private void validateDuplicateMember(AddMemberRequest request) {
        Member findMember = memberRepository.findByEmail(request.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Member saveMember(@Valid AddMemberRequest request) {
        validateDuplicateMember(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);
        return memberRepository.save(request.toEntity());
    }

    public Member loginCheck(String email, String rawPassword) {
        // 이메일로 회원 조회
        Member member = memberRepository.findByEmail(email);
    
        // 회원이 존재하지 않을 경우 예외 발생
        if (member == null) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }
    
        // 입력된 비밀번호와 저장된 비밀번호가 일치하지 않을 경우 예외 발생
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    
        // 인증 성공 시 회원 객체 반환
        return member;
    }
}
