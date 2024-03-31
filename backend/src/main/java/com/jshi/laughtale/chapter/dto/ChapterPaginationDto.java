package com.jshi.laughtale.chapter.dto;

import lombok.Builder;
import lombok.Getter;

public class ChapterPaginationDto {

    @Getter
    @Builder
    public static class Response {
        private Integer nextPage;
        private Integer prevPage;
    }
}
