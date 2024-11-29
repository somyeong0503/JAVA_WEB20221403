package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.domain.Board;
import com.example.demo.model.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

/**
 * BlogService
 * 게시판 서비스 로직 처리:
 * - 게시글 조회 (전체, 페이징, 키워드 검색)
 * - 게시글 저장, 수정, 삭제
 * - Spring Data JPA와 Pageable 활용
 */
@Service
@RequiredArgsConstructor
public class BlogService {

    private final BoardRepository blogRepository;

    // 게시판 전체 목록 조회
    public List<Board> findAll() {
        return blogRepository.findAll();
    }

    // 페이징 처리된 게시판 목록 조회
    public Page<Board> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    // 키워드로 게시글 검색 (페이징 포함)
    public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    // 특정 게시글 조회
    public Optional<Board> findById(Long id) {
        return blogRepository.findById(id);
    }

    // 새 게시글 저장
    public Board save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

     // 게시글 수정
     public void update(Long id, AddArticleRequest request) {
        blogRepository.findById(id).ifPresent(board -> {
            board.update(
                request.getTitle(),
                request.getContent(),
                request.getUser(),
                request.getNewdate(),
                request.getCount(),
                request.getLikec()
                
            );
            blogRepository.save(board);
        });
    }

    // 게시글 삭제
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

}
