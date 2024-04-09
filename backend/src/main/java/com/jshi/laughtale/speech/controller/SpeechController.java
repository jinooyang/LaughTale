package com.jshi.laughtale.speech.controller;

import com.jshi.laughtale.speech.dto.SpeechDetail;
import com.jshi.laughtale.speech.service.SpeechService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech")
public class SpeechController {

    private final SpeechService speechService;

    @GetMapping("/{id}")
    public ResponseEntity<SpeechDetail.Response> getSpeechWithCut(@PathVariable Long id) {
        return ResponseEntity.ok(speechService.loadBySpeechDetail(id));
    }

}
