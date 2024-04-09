package com.jshi.laughtale.wordbook.controller;

import com.jshi.laughtale.security.details.CustomUserDetails;
import com.jshi.laughtale.wordbook.dto.WordBookBasic;
import com.jshi.laughtale.wordbook.service.WordBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/word-book")
@RequiredArgsConstructor
public class WordBookController {

    private final WordBookService wordBookService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> addWord(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        wordBookService.addWord(id, customUserDetails.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{level}")
    public ResponseEntity<Page<WordBookBasic.Response>> getWordBook(
            @PathVariable int level,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size
    ) {
        Page<WordBookBasic.Response> wordBook = wordBookService.loadWordBook(level, customUserDetails.getId(), page, size);
        return ResponseEntity.ok(wordBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeWordBook(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        wordBookService.deleteWordBook(id, customUserDetails.getId());
        return ResponseEntity.ok().build();
    }
}
