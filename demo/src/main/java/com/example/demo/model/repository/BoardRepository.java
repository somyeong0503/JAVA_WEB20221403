package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 대소문자 구분 없이 제목에 키워드가 포함된 게시글 검색
    Page<Board> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
