package com.jshi.laughtale.wordbook.dto;

import lombok.Builder;
import lombok.Getter;

public class WordBookBasic {

    @Builder
    @Getter
    public static class Response {
        private Long id;
        private String word;
        private String partOfSpeech;
        private String definition;
    }
}
