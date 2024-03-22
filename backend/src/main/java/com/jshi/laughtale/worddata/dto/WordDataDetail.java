package com.jshi.laughtale.worddata.dto;

import com.jshi.laughtale.speech.dto.SpeechBasic;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class WordDataDetail {

    @Builder
    @Getter
    public static class Response {
        private String word;
        private String definition;
        private String partOfSpeech;
        private List<SpeechBasic.Response> speeches;
    }
}
