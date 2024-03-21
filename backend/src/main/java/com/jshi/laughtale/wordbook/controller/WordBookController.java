package com.jshi.laughtale.wordbook.controller;

import com.jshi.laughtale.wordbook.service.WordBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wordbook")
@RequiredArgsConstructor
public class WordBookController {

    private final WordBookService wordBookService;
}
