package com.jshi.laughtale.worddata.controller;

import com.jshi.laughtale.worddata.dto.WordCloudData;
import com.jshi.laughtale.worddata.dto.WordDataDetail;
import com.jshi.laughtale.worddata.service.WordDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/word-data")
public class WordDataController {

    private final WordDataService wordDataService;

    @GetMapping("/{id}")
    public ResponseEntity<WordDataDetail.Response> getWordData(@PathVariable Long id) {
        return ResponseEntity.ok(wordDataService.loadWordDataDetail(id));
    }

    @GetMapping("/speech/{speechId}")
    public ResponseEntity<List<WordDataDetail.Response>> getWords(@PathVariable Long speechId) {
        return ResponseEntity.ok(wordDataService.loadWordDataDetailWithoutSpeech(speechId));
    }

    @GetMapping("/word-cloud")
    public ResponseEntity<List<WordCloudData.Response>> getWordCloudData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        return ResponseEntity.ok(wordDataService.loadWordCloudData(page, size));
    }

    @GetMapping("/random")
    public ResponseEntity<List<WordDataDetail.Response>> getRandomWord() {
        return ResponseEntity.ok(wordDataService.loadRandomWord());
    }
}
