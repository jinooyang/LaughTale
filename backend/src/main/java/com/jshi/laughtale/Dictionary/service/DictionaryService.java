package com.jshi.laughtale.Dictionary.service;

import org.springframework.stereotype.Service;

import com.jshi.laughtale.Dictionary.repository.DictionaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionaryService {
	private final DictionaryRepository dictionaryRepository;



}
