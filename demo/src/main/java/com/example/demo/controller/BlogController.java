package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.AddBoardRequest;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.domain.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.example.demo.model.domain.Board;



@Controller
@Configuration
@ControllerAdvice
public class BlogController {

    @Autowired
    BlogService blogService; // 서비스 계층 의존성 주입

    // 게시글 목록 조회
    // @GetMapping("/article_list")
    // public String articleList(Model model) {
    //     List<Article> list = blogService.findAll(); // 게시판 리스트 조회
    //     model.addAttribute("articles", list); // 모델에 게시글 목록 추가
    //     return "article_list"; // article_list.html 템플릿 반환
    // }


    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model) {
    List<Board> list = blogService.findAll(); // 게시판 전체 리스트
        model.addAttribute("boards", list); // 모델에 추가
        return "board_list"; // .HTML 연결
    }

    // // 게시글 수정 페이지로 이동
    // @GetMapping("/article_edit/{id}")
    // public String article_edit(Model model, @PathVariable Long id) {
    //     Optional<Article> article = blogService.findById(id); // 선택한 게시판 글 조회
    //     if (article.isPresent()) {
    //         model.addAttribute("article", article.get()); // 존재하면 Article 객체를 모델에 추가
    //     } else {
    //         throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + id);
    //     }
    //     return "article_edit"; // article_edit.html로 연결
    // }

    // // 게시글 수정 처리
    // @PutMapping("/api/article_edit/{id}")
    // public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
    //     blogService.update(id, request); // 서비스 메서드를 호출하여 글 수정
    //     return "redirect:/article_list"; // 글 수정 후 게시글 목록 페이지로 리다이렉트
    // }

    @DeleteMapping("/api/article_delete/{id}")
        public String deleteArticle(@PathVariable Long id) {
            blogService.delete(id);
            return "redirect:/article_list";
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

     // IllegalArgumentException 발생 시 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_page/article_error"; // 오류 페이지로 이동
    }

    // MethodArgumentTypeMismatchException 발생 시 처리 (잘못된 타입의 PathVariable 처리)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("errorMessage", "잘못된 요청입니다. ID는 숫자여야 합니다.");
        return "error_page/article2_error"; // 오류 페이지로 이동
    }

    @GetMapping("/board_view/{id}") // 게시판 링크 지정
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findById(id); // 선택한 게시판 글
        if (list.isPresent()) {
            model.addAttribute("boards", list.get()); // 존재할 경우 실제 Article 객체를 모델에 추가
            } else {
            // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결
            }
            return "board_view"; // .HTML 연결
            }

            @GetMapping("/board_edit/{id}") // 게시판 편집 페이지로 이동
            public String editBoard(@PathVariable Long id, Model model) {
                // 선택한 게시판 글 조회
                Board board = blogService.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID: " + id));
            
                // Board 객체를 모델에 추가
                model.addAttribute("board", board);
                return "board_edit"; // board_edit.html로 연결
            }
            
            @PutMapping("/api/board_edit/{id}") // 게시글 수정 요청
            public String updateBoard(@PathVariable Long id, @ModelAttribute AddBoardRequest request) {
                blogService.update(id, request); // 수정 로직 실행
                return "redirect:/board_list"; // 수정 후 게시판 목록 페이지로 리다이렉트
            }
            
            @PostMapping("/api/boards") // 새로운 게시글 추가 요청
            public String addBoard(@ModelAttribute AddBoardRequest request) {
                blogService.save(request); // 새로운 게시글 저장
                return "redirect:/board_list"; // 저장 후 게시판 목록 페이지로 리다이렉트
            }
            
            @DeleteMapping("/api/board_delete/{id}")
            public String deleteBoard(@PathVariable Long id) {
                blogService.delete(id);
                return "redirect:/board_list";
    }

    }
