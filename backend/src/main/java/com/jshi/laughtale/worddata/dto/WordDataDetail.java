package com.jshi.laughtale.worddata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jshi.laughtale.speech.dto.SpeechBasic;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class WordDataDetail {

    @Builder
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        private Long id;
        private String word;
        private String definition;
        private String partOfSpeech;
        private Integer level;
        private List<SpeechBasic.Response> speeches;
    }
}
