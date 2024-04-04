package com.jshi.laughtale.chapter.dto;

import lombok.Builder;
import lombok.Getter;

public class ChapterFirst {

    @Builder
    @Getter
    public static class Response {
        private Long id;
    }
}
