package com.jshi.laughtale.chapter.controller;

import com.jshi.laughtale.chapter.dto.ChapterFirst;
import com.jshi.laughtale.chapter.dto.ChapterLevelDto;
import com.jshi.laughtale.chapter.dto.ChapterListDto;
import com.jshi.laughtale.chapter.dto.ChapterPaginationDto;
import com.jshi.laughtale.chapter.service.ChapterService;
import com.jshi.laughtale.common.dto.LevelCount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("/list")
    public ResponseEntity<Page<ChapterListDto.Response>> getMangaChapters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam("mangaid") long mangaid) {
        return ResponseEntity.ok(chapterService.getChaptersFromManga(mangaid, page, size));
    }

    @GetMapping("/levels")
    public ResponseEntity<List<ChapterLevelDto.Response>> getChapterLevels(
            @RequestParam("mangaId") long mangaId) {
        return ResponseEntity.ok(chapterService.getChapterLevels(mangaId));
    }

    @GetMapping("/level")
    public ResponseEntity<List<LevelCount.Response>> getChapterLevelCount(
            @RequestParam("chapterId") long chapterId) {
        return ResponseEntity.ok(chapterService.getChapterLevelCount(chapterId));
    }

    @GetMapping("/first/{mangaId}")
    public ResponseEntity<ChapterFirst.Response> getFirstChapter(@PathVariable Long mangaId) {
        return ResponseEntity.ok(chapterService.loadFirstChapter(mangaId));
    }


    @GetMapping("/{chapterId}")
    public ResponseEntity<ChapterPaginationDto.Response> getChapterPagination(
            @PathVariable Long chapterId
    ) {
        return ResponseEntity.ok(chapterService.getChapterPagination(chapterId));
    }
}
