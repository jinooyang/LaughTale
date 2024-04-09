package com.jshi.laughtale.worddata.mapper;

import com.jshi.laughtale.speech.dto.SpeechBasic;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.worddata.dto.WordCloudData;
import com.jshi.laughtale.worddata.dto.WordDataDetail;

import java.util.List;

public class WordDataMapper {

    private static final int INIT_LEVEL = 5;
    private static final int INIT_FREQUENCY = 0;

    public static WordData toEntity(String word, String partOfSpeech) {
        return WordData.builder()
                .word(word)
                .partOfSpeech(partOfSpeech)
                .level(INIT_LEVEL)
                .frequency(INIT_FREQUENCY)
                .build();
    }

    public static WordData toEntity(String word, String partOfSpeech, String definition) {
        return WordData.builder()
                .word(word)
                .partOfSpeech(partOfSpeech)
                .definition(definition)
                .frequency(INIT_FREQUENCY)
                .level(INIT_LEVEL)
                .build();
    }

    public static WordDataDetail.Response toDetailResponse(WordData wordData, List<SpeechBasic.Response> speechList) {
        return WordDataDetail.Response.builder()
                .word(wordData.getWord())
                .partOfSpeech(wordData.getPartOfSpeech())
                .definition(wordData.getDefinition())
                .speeches(speechList)
                .level(wordData.getLevel())
                .build();
    }

    public static WordDataDetail.Response toDetailResponse(WordData wordData) {
        return WordDataDetail.Response.builder()
                .id(wordData.getId())
                .level(wordData.getLevel())
                .word(wordData.getWord())
                .definition(wordData.getDefinition())
                .build();
    }

    public static WordCloudData.Response toWordCloudResponse(WordData wordData) {
        return WordCloudData.Response.builder().text(wordData.getWord()).value(wordData.getFrequency())
                .build();
    }
}
