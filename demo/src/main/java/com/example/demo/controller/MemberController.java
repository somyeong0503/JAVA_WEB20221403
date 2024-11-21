package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.service.MemberService;
import com.example.demo.model.domain.Member;

import jakarta.validation.Valid;

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

    @GetMapping("/member_login") // 로그인 페이지 연결
public String member_login() {
    return "login"; // .HTML 연결
}

@PostMapping("/api/login_check") // 로그인(아이디, 패스워드) 체크
public String checkMembers(@ModelAttribute AddMemberRequest request, Model model) {
    try {
        // 이메일과 패스워드를 기반으로 로그인 체크
        Member member = memberService.loginCheck(request.getEmail(), request.getPassword());

        // 로그인 성공 시 회원 정보를 모델에 추가
        model.addAttribute("member", member);

        // 로그인 성공 후 게시판 리스트 페이지로 리다이렉트
        return "redirect:/board_list";
    } catch (IllegalArgumentException e) {
        // 로그인 실패 시 에러 메시지를 모델에 추가
        model.addAttribute("error", e.getMessage());

        // 로그인 실패 시 로그인 페이지로 리다이렉트
        return "login";
    }
}

}
