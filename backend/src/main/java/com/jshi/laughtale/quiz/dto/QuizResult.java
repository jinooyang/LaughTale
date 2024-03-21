package com.jshi.laughtale.quiz.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

public class QuizResult {
	@Getter
	@Builder
	public static class Request {
		private List<Integer> result;
	}

	@Getter
	@Builder
	public static class Response {
		private List<Boolean> result;
	}
}
