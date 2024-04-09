package com.jshi.laughtale.manga.controller;

import com.jshi.laughtale.common.dto.LevelCount;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.dto.*;
import com.jshi.laughtale.manga.service.MangaService;
import com.jshi.laughtale.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/manga")
public class MangaController {

    private final MangaService mangaService;

    @PostMapping("/upload")
    public ResponseEntity<MangaAnalyze.Response> upload(
            @RequestPart MultipartFile thumbnail,
            @RequestPart MangaUpload.Request manga,
            @RequestPart List<MultipartFile> files
    ) throws IOException {
        log.info("만화 업로드 요청...");
        return ResponseEntity.ok(mangaService.upload(thumbnail, manga, files));
    }

    @PostMapping("/analyze")
    public ResponseEntity<MangaAnalyze.Response> analyze(@RequestPart List<MultipartFile> files) throws IOException {
        log.info("만화 분석 요청...");
        return ResponseEntity.ok(mangaService.analyzeManga(files));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<RecentManga.Response>> getRecentManga(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        List<RecentManga.Response> mangaList = mangaService.getRecentManga(
                customUserDetails.getId());
        return ResponseEntity.ok(mangaList);
    }

    @GetMapping
    public ResponseEntity<List<List<LevelManga.Response>>> getLevelManga(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "4") Integer size
    ) {
        List<List<LevelManga.Response>> mangaList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            mangaList.add(mangaService.getLevelManga(i, page, size).getContent());
        }
        return ResponseEntity.ok(mangaList);
    }

    @GetMapping("/{level}")
    public ResponseEntity<Page<LevelManga.Response>> getMangaByLevel(
            @PathVariable Integer level,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "12") Integer size) {
        return ResponseEntity.ok(mangaService.getLevelManga(level, page, size));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<MangaInfo.Response> getMangaInfo(
            @PathVariable Long id) {
        Manga manga = mangaService.getMangaInfo(id);
        return ResponseEntity.ok(MangaInfo.Response.builder()
                .id(manga.getId())
                .title(manga.getTitle())
                .category(manga.getCategory())
                .author(manga.getAuthor())
                .summary(manga.getDescription())
                .thumbnail(manga.getThumbnail())
                .level(manga.getLevel())
                .build());
    }

    @GetMapping("/word/level")
    public ResponseEntity<List<LevelCount.Response>> getMangaWordLevelCount(
            @RequestParam("mangaId") long mangaId) {
        return ResponseEntity.ok(mangaService.getMangaWordLevelCount(mangaId));
    }

    @GetMapping("/chapter/level")
    public ResponseEntity<List<LevelCount.Response>> getMangaChapterLevelCount(
            @RequestParam("mangaId") long mangaId) {
        return ResponseEntity.ok(mangaService.getMangaChapterLevelCount(mangaId));
    }
}
