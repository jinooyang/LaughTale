package com.jshi.laughtale.wordhistory.mapper;

import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.wordhistory.domain.WordHistory;
import com.jshi.laughtale.wordhistory.dto.WordHistoryDto;

public class WordHistoryMapper {
    public static WordHistoryDto.MemberWordData toMemberWordData(WordHistory wordHistory) {
        WordData wordData = wordHistory.getWordData();
        return WordHistoryDto.MemberWordData.builder()
                .level(wordData.getLevel())
                .time(wordHistory.getStudyDate())
                .word(wordData.getWord())
                .wordId(wordData.getId())
                .offset(wordHistory.getOffset())
                .repeatCnt(wordHistory.getStudyCnt())
                .build();

    }

}
