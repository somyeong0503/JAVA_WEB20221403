package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.domain.Article;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService; // 서비스 계층 의존성 주입

    @GetMapping("/article_list") // 게시판 링크 지정
    public String articleList(Model model) {
        List<Article> list = blogService.findAll(); // 게시판 리스트 조회
        model.addAttribute("articles", list); // 모델에 게시글 목록 추가
        return "article_list"; // article_list.html 템플릿 반환
    }
    @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    public String article_edit(Model model, @PathVariable Long id) {
        Optional<Article> list = blogService.findById(id); // 선택한 게시판 글
        if (list.isPresent()) {
        model.addAttribute("article", list.get()); // 존재하면 Article 객체를 모델에 추가
        } else {
        // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
        return "error"; // 오류 처리 페이지로 연결
        }
        return "article_edit"; // .HTML 연결
    }
}
