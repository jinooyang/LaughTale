package com.jshi.laughtale.wordbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.jshi.laughtale.wordbook.repository.WordBookRepository;

@Service
@RequiredArgsConstructor
public class WordBookService {
    private final WordBookRepository wordBookRepository;

}
