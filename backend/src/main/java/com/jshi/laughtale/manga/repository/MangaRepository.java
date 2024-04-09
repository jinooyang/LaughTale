package com.jshi.laughtale.manga.repository;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.dto.LevelManga;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MangaRepository extends JpaRepository<Manga, Long> {

    @Query(value = "SELECT C.chapterNo " +
            "FROM Manga AS M " +
            "JOIN Chapter AS C ON C.manga.id = M.id " +
            "WHERE M.title = :title " +
            "ORDER BY C.chapterNo DESC " +
            "LIMIT 1")
    Optional<Integer> findLastChapterByManga(String title);

    Optional<Manga> findByTitle(String title);


    @Query(value = "SELECT m.title, m.thumbnail, m.id \n"
            + "FROM view_history v, manga m ,chapter c\n"
            + "WHERE v.member_id = :memberId and v.chapter_id = c.id and c.manga_id = m.id\n"
            + "GROUP BY m.id\n"
            + "ORDER BY MAX(v.view_date) desc LIMIT 10", nativeQuery = true)
    List<Tuple> findRecentManga(Long memberId);

    // Page<LevelManga.Response> findAllByLevel(int level, Pageable pageable);
    Page<LevelManga.Response> findAllByLevelOrderByIdDesc(int level, Pageable pageable);

    @Query("SELECT m FROM Manga m JOIN FETCH m.chapter c WHERE m.id = :id")
    Manga findMangaByIdFetchJoinChapter(@Param("id") Long id);

    Manga findByChapter(Chapter chapter);
}
