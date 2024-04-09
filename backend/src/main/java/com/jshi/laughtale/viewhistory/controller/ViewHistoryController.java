package com.jshi.laughtale.viewhistory.controller;

import com.jshi.laughtale.security.details.CustomUserDetails;
import com.jshi.laughtale.viewhistory.dto.ChapterHistoryDto;
import com.jshi.laughtale.viewhistory.service.ViewHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@Slf4j
public class ViewHistoryController {

    private final ViewHistoryService viewHistoryService;

    @PostMapping("/{chapterId}")
    public ResponseEntity<Void> createHistory(
            @PathVariable Long chapterId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("create History : {} memberId : {}", chapterId, customUserDetails.getId());
        viewHistoryService.createHistory(chapterId, customUserDetails.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{mangaId}")
    public ResponseEntity<ChapterHistoryDto.Response> getViewHistory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable long mangaId
    ) {
        List<Long> chaptersViewed = viewHistoryService.getMangaChapterViewHistory(customUserDetails.getId(), mangaId);
        ChapterHistoryDto.Response response =
                ChapterHistoryDto.Response
                        .builder()
                        .memberId(customUserDetails.getId())
                        .mangaId(mangaId)
                        .chaptersViewed(chaptersViewed)
                        .build();
        return ResponseEntity.ok(response);
    }
}
