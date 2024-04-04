package com.jshi.laughtale.viewhistory.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ChapterHistoryDto {


    @Getter
    @Builder
    public static class Request {

    }

    @Getter
    @Builder
    public static class Response {
        private Long memberId;
        private Long mangaId;
        private List<Long> chaptersViewed;
    }
}
