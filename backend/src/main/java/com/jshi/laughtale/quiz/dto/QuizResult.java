package com.jshi.laughtale.quiz.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

public class QuizResult {
	@Getter

	public static class Request {
		private List<Integer> answer;
	}

	@Getter
	@Builder
	public static class Response {
		private List<Boolean> result;
	}
}
