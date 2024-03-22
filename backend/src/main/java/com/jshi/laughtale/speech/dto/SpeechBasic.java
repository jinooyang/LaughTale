package com.jshi.laughtale.speech.dto;

import lombok.Builder;
import lombok.Getter;

public class SpeechBasic {

    @Builder
    @Getter
    public static class Response {
        private Long id;
        private String sentence;
        private String translate;
    }
}
