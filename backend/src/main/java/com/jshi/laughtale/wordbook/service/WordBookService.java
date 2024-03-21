package com.jshi.laughtale.wordbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordBookService {
    private final WordBookRepository wordBookRepository;
}
