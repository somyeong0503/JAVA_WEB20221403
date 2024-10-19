// package com.example.demo.controller;

// import com.example.demo.model.domain.Article;
// import com.example.demo.model.service.AddArticleRequest;
// import com.example.demo.model.service.BlogService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RequiredArgsConstructor
// @RestController // @Controller + @ResponseBody
// public class BlogRestController {

//     private final BlogService blogService;

//     @PostMapping("/api/articles")
//     public ResponseEntity<Article> addArticle(@ModelAttribute AddArticleRequest request) {
//         Article saveArticle = blogService.save(request);
//         return ResponseEntity.status(HttpStatus.CREATED)
//                              .body(saveArticle);
//     }
//     @GetMapping("/favicon.ico")
// public void favicon() {
// // 아무 작업도 하지 않음
// }
// }

package com.example.demo.controller;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@Controller // @Controller 어노테이션 사용
public class BlogRestController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public String addArticle(@ModelAttribute AddArticleRequest request, Model model) {
        Article saveArticle = blogService.save(request);
        model.addAttribute("article", saveArticle);  // 저장된 Article을 모델에 추가
        return "articleDetails";  // articleDetails.html 템플릿으로 리턴 (추가한 게시글 정보 표시)
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
        // 아무 작업도 하지 않음
    }
}
