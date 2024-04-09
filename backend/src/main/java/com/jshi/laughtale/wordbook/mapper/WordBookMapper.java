package com.jshi.laughtale.wordbook.mapper;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.wordbook.domain.WordBook;
import com.jshi.laughtale.wordbook.dto.WordBookBasic;
import com.jshi.laughtale.worddata.domain.WordData;

public class WordBookMapper {

    public static WordBook toEntity(WordData wordData, Member member) {
        return WordBook.builder()
                .member(member)
                .wordData(wordData)
                .build();
    }

    public static WordBookBasic.Response toBasicResponse(WordBook wordBook) {
        WordData wordData = wordBook.getWordData();
        return WordBookBasic.Response.builder()
                .id(wordData.getId())
                .word(wordData.getWord())
                .definition(wordData.getDefinition())
                .partOfSpeech(wordData.getPartOfSpeech())
                .build();
    }
}
