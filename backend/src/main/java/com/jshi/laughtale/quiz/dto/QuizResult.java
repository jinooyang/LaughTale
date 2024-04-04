package com.jshi.laughtale.quiz.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
