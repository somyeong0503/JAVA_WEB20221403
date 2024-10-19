package com.example.demo.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.domain.Article;
import com.example.demo.model.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service // 서비스 계층임을 명시하여 Spring이 관리할 수 있도록 함
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성
public class BlogService {

    private final BlogRepository blogRepository; // 리포지토리 의존성 주입

    // 게시판 전체 목록 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    public Article save(AddArticleRequest request){
        // DTO가 없는 경우 이곳에 직접 구현 가능
        // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
        // Article article = Article.builder()
        // .title(title)
        // .content(content)
        // .build();
        return blogRepository.save(request.toEntity());
        }
        public Optional<Article> findById(Long id) {
            return blogRepository.findById(id);
        }
        
}
