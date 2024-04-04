package com.jshi.laughtale.cut.repository;


import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.cut.domain.Cut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CutRepository extends JpaRepository<Cut, Long> {
    Page<Cut> findAllByChapter(Chapter chapter, Pageable pageable);
}

