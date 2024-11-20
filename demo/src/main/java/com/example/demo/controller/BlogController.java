package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

    // 게시글 보기
    @GetMapping("/board_view/{id}")
    public String boardView(@PathVariable Long id, Model model) {
        Board board = blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + id));
        model.addAttribute("boards", board);
        return "board_view";
    }

    // 게시글 쓰기 페이지
    @GetMapping("/board_write")
    public String boardWrite() {
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
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
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

    @GetMapping("/board_list") // 새로운 게시판 링크지정
public String boardList(
        Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "") String keyword) {

    int pageSize = 3; // 한 페이지의 게시글 수
    PageRequest pageable = PageRequest.of(page, pageSize);

    Page<Board> list = keyword.isEmpty()
            ? blogService.findAll(pageable)
            : blogService.searchByKeyword(keyword, pageable);

    // 시작 번호 계산
    int startNum = (page * pageSize) + 1;

    model.addAttribute("boards", list);
    model.addAttribute("totalPages", list.getTotalPages());
    model.addAttribute("currentPage", page); // currentPage 추가
    model.addAttribute("startNum", startNum); // 시작 번호 추가
    model.addAttribute("keyword", keyword);
    return "board_list";
}


}
