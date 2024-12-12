package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.service.MemberService;
import com.example.demo.model.domain.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.servlet.http.Cookie;  // Cookie 클래스를 import
import java.util.UUID;


/**
 * MemberController
 * 회원 가입 관련 요청을 처리하는 컨트롤러
 */
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 생성자 주입을 통한 MemberService 의존성 주입
     * @param memberService 회원 비즈니스 로직을 처리하는 서비스
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 가입 페이지로 연결
     * @return 회원 가입 페이지 HTML 파일 이름
     */
    @GetMapping("/join_new")
    public String joinNew() {
        return "join_new"; // join_new.html 템플릿 반환
    }

    /**
     * 회원 가입 요청 처리
     * @param request 회원 가입 요청 DTO
     * @return 회원 가입 완료 페이지 HTML 파일 이름
     */
    @PostMapping("/api/members")
    public String addMembers(@Valid @ModelAttribute AddMemberRequest request) {
        memberService.saveMember(request); // 요청 데이터를 저장
        return "join_end"; // join_end.html 템플릿 반환
    }

    // 로그인 페이지 요청 처리
// GET 요청으로 "/member_login" URL에 매핑
@GetMapping("/member_login")
public String member_login() {
    return "login"; // login.html 템플릿 반환
}


@PostMapping("/api/login_check")
public String checkMembers(@ModelAttribute AddMemberRequest request, Model model, HttpServletRequest request2, HttpServletResponse response) {
    try {
        // 1. 이메일과 패스워드를 기반으로 로그인 체크
        Member member = memberService.loginCheck(request.getEmail(), request.getPassword());

        // 2. 기존 세션 무효화 및 새로운 세션 생성
        HttpSession session = request2.getSession(false); // 기존 세션 가져오기
        if (session != null) {
            session.invalidate(); // 기존 세션 무효화
        }
        session = request2.getSession(true); // 새로운 세션 강제 생성

        // 3. 사용자 고유 정보 저장
        String sessionId = UUID.randomUUID().toString(); // 고유 세션 ID 생성
        session.setAttribute("userId", sessionId); // 고유 ID 저장
        session.setAttribute("email", request.getEmail()); // 이메일 저장
        session.setAttribute("loginTime", System.currentTimeMillis()); // 로그인 시간 저장

        // 4. 세션 정보 로그 출력
        System.out.println("Session ID: " + session.getId());
        System.out.println("User Email: " + request.getEmail());

        // 5. 로그인 성공 시 회원 정보를 모델에 추가
        model.addAttribute("member", member);

        // 6. 로그인 성공 후 게시판 리스트 페이지로 리다이렉트
        return "redirect:/board_list";

    } catch (IllegalArgumentException e) {
        // 로그인 실패 시 에러 메시지를 모델에 추가
        model.addAttribute("error", e.getMessage());

        // 로그인 실패 시 로그인 페이지로 리다이렉트
        return "login";
    }
}

@GetMapping("/api/logout") // 로그아웃 버튼 동작
public String memberLogout(Model model, HttpServletRequest request, HttpServletResponse response) {
    try {
        // 1. 기존 세션 가져오기 (존재하지 않으면 null 반환)
        HttpSession session = request.getSession(false);

        // 2. 현재 사용자의 세션과 쿠키만 삭제 처리
        if (session != null) {
            session.invalidate(); // 현재 세션 무효화

            // JSESSIONID 쿠키 삭제
            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setPath("/"); // 쿠키 경로 설정
            cookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정하여 삭제
            response.addCookie(cookie); // 쿠키 응답에 추가
        }

        // 3. 로그아웃 로그 출력 (디버그용)
        System.out.println("사용자 로그아웃 처리 완료");

        return "login"; // 로그인 페이지로 리다이렉트

    } catch (IllegalArgumentException e) {
        model.addAttribute("error", e.getMessage()); // 에러 메시지 전달
        return "login"; // 로그인 실패 시 로그인 페이지로 리다이렉트
    }
}



}
