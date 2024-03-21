package com.jshi.laughtale.dictionary.service;

import org.springframework.stereotype.Service;

import com.jshi.laughtale.dictionary.repository.DictionaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionaryService {
	private final DictionaryRepository dictionaryRepository;



}
