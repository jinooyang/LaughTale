package com.jshi.laughtale.wordhistory.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class WordHistoryDto {
    // @Getter
    //
    // public static class Request {
    //
    // }

    @Getter
    @Builder
    public static class Response {
        private Long memberId;
        private List<MemberWordData> historyList;
    }

    // 각 단어의 레벨과
    // 각 단어의 학습한 시간
    // 각 단어
    // 각 단어의 PK
    // //각 단어의 오프셋
    // 각 단어의 학습한 횟수
    @Getter
    @Builder
    public static class MemberWordData {
        private Integer level;
        private LocalDateTime time;
        private String word;
        private Long wordId;
        private Integer offset;
        private Integer repeatCnt;
    }

}
