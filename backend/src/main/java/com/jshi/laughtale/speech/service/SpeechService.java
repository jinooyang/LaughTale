package com.jshi.laughtale.speech.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.speech.repository.SpeechRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpeechService {
	private final SpeechRepository speechRepository;

	public Speech findById(Long id){
		return speechRepository.findById(id).orElseThrow();
	}
}
