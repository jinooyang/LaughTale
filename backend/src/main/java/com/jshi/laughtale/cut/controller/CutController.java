package com.jshi.laughtale.cut.controller;

import com.jshi.laughtale.cut.dto.View;
import com.jshi.laughtale.cut.mapper.CutMapper;
import com.jshi.laughtale.cut.service.CutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cut")
@Slf4j
public class CutController {

    private final CutService cutService;

    @PostMapping("/images")
    public ResponseEntity<Page<View.Response>> readChapter(@RequestBody View.Request request) {
        Long chapterId = request.getChapterId();

        return ResponseEntity.ok(
                cutService.findCutsByChapter(chapterId, request.getPage(), request.getSize())
                        .map(CutMapper::toChapterImage));
    }
}
