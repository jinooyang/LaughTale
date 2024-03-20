package com.jshi.laughtale.myquiz.service;

import org.springframework.stereotype.Service;

import com.jshi.laughtale.choice.repository.ChoiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
	private final ChoiceRepository quizRepository;

	public void makeNewQuiz() {
	}
}
