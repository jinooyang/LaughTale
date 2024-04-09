package com.jshi.laughtale.wordhistory.controller;

import com.jshi.laughtale.security.details.CustomUserDetails;
import com.jshi.laughtale.wordhistory.dto.WordHistoryDto;
import com.jshi.laughtale.wordhistory.service.WordHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wordhistory")
public class WordHistoryController {
    private final WordHistoryService wordHistoryService;

    @GetMapping("/data")
    public ResponseEntity<WordHistoryDto.Response> getMemberWordHistory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(wordHistoryService.getMemberWordHistoryData(customUserDetails.getId()));
    }
}
