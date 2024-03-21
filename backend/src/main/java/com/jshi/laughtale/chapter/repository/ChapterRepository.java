package com.jshi.laughtale.chapter.repository;

import com.jshi.laughtale.chapter.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
