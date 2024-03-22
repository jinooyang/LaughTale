package com.jshi.laughtale.chapter.repository;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.manga.domain.Manga;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
	Page<Chapter> findAllByMangaOrderByChapterNoAsc(Manga manga, Pageable pageable);
}
