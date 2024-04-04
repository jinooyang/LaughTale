package com.jshi.laughtale.manga.dto;

import lombok.Builder;
import lombok.Getter;

public class RecentManga {
    @Getter
    @Builder
    public static class Request {

    }

    @Getter
    @Builder
    public static class Response {
        private String thumbnail;
        private String title;
        private Long id;
    }
}
