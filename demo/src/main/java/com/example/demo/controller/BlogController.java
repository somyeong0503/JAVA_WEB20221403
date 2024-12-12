package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.data.domain.Page;

import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.example.demo.model.domain.Board;

@Controller
@Configuration
@ControllerAdvice
public class BlogController {

    @Autowired
    private BlogService blogService;

    // 게시판 목록 조회
    @GetMapping("/board_list/basic")
public String boardListBasic(Model model) {
    List<Board> list = blogService.findAll();
    model.addAttribute("boards", list);
    return "board_list";
}

// 특정 게시글 상세 보기 처리
// GET 요청으로 "/board_view/{id}" URL에 매핑
@GetMapping("/board_view/{id}")
public String boardView(@PathVariable String id, HttpSession session, Model model) {
    try {
        Long longId = Long.parseLong(id); // 문자열을 Long으로 변환
        Board board = blogService.findById(longId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + longId));
        
        // 로그인한 사용자 이메일 가져오기
        String loggedInUser = (String) session.getAttribute("email");
        System.out.println("[DEBUG] 현재 로그인한 사용자: " + loggedInUser);

        // 모델에 게시글과 로그인 사용자 정보 추가
        model.addAttribute("boards", board);
        model.addAttribute("loggedInUser", loggedInUser); // 현재 로그인한 사용자 추가
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("ID는 숫자여야 합니다.");
    }
    return "board_view";
}

// 게시글 작성 화면 처리
// GET 요청으로 "/board_write" URL에 매핑
@GetMapping("/board_write")
public String boardWrite(HttpSession session, Model model) {
    // 세션에서 이메일 정보 가져오기
    String email = (String) session.getAttribute("email");
    System.out.println("[DEBUG] 로그인된 사용자 이메일: " + email);

    // 이메일이 없는 경우 기본값 설정
    if (email == null || email.isEmpty()) {
        email = "guest@example.com"; // 기본값
    }

    // 모델에 이메일 추가
    model.addAttribute("email", email);
    return "board_write";
}

    // 게시글 저장
    @PostMapping("/api/boards")
    public String addBoard(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/board_list";
    }

    // 게시글 편집 페이지 이동
    @GetMapping("/board_edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        Board board = blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + id));
        model.addAttribute("board", board);
        return "board_edit";
    }

    // 게시글 수정 요청
    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(@Valid @PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list";
    }

    // 게시글 삭제
    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list";
    }

    // 오류 처리: IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_page/article_error";
    }

    // 오류 처리: 잘못된 요청 타입
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("errorMessage", "잘못된 요청입니다. ID는 숫자여야 합니다.");
        return "error_page/article2_error";
    }

    // HiddenHttpMethodFilter 등록
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    // 게시판 목록 조회를 처리하는 메서드
    // GET 요청으로 "/board_list" URL에 매핑
    @GetMapping("/board_list")
    public String boardList(Model model, 
                            @RequestParam(defaultValue = "0") int page, 
                            @RequestParam(defaultValue = "") String keyword, 
                            HttpSession session) {
        // 세션에서 사용자 ID 및 이메일 확인
        String userId = (String) session.getAttribute("userId");
        String uEmail = (String) session.getAttribute("email");
        
        if (userId == null || uEmail == null) {
            return "redirect:/member_login"; // 로그인되지 않은 사용자 처리
        }
    
        int pageSize = 3;  // 한 페이지의 게시글 수
        PageRequest pageable = PageRequest.of(page, pageSize);
    
        // 키워드 유무에 따라 전체 조회 또는 키워드 검색 수행
        Page<Board> list = keyword.isEmpty()
                ? blogService.findAll(pageable)
                : blogService.searchByKeyword(keyword, pageable);
        
        // 시작 번호 계산
        int startNum = (page * pageSize) + 1;
        
        // 모델에 데이터 추가
        model.addAttribute("boards", list);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("startNum", startNum);
        model.addAttribute("email", uEmail);  // 이메일을 uEmail로 변경
        model.addAttribute("keyword", keyword);
    
        return "board_list";  // board_list.html 템플릿 반환
    }

}
