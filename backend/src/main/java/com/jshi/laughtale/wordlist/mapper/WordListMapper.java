package com.jshi.laughtale.wordlist.mapper;

import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.wordlist.domain.WordList;

public class WordListMapper {

    public static WordList toEntity(Speech speech, WordData wordData) {
        return WordList.builder()
                .speech(speech)
                .wordData(wordData)
                .build();
    }
}
