package com.jshi.laughtale.chapter.repository;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.manga.domain.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Page<Chapter> findAllByMangaOrderByChapterNoDesc(Manga manga, Pageable pageable);

    @Query(value = "select c.* from chapter c , manga m where c.manga_id = m.id and m.title=:title"
            + " and c.chapter_no = :chapterNo ", nativeQuery = true)
    Optional<Chapter> findChapterByMangaTitleAndChapterNo(String title, Integer chapterNo);

    List<Chapter> findAllByMangaId(Long mangaId);

    @Query("SELECT c FROM Chapter c WHERE c.manga.id = :mangaId ORDER BY c.id ASC LIMIT 1")
    Optional<Chapter> findFirstChapterByMangaId(Long mangaId);

}
