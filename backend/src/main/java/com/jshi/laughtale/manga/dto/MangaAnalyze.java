package com.jshi.laughtale.manga.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class MangaAnalyze {

    @Getter
    @Builder
    @ToString
    public static class Request {
        private String title;
        private Integer chapterNo;
        private String author;
        private String category;
        private String description;
        private String thumbnail;
        private List<String> filenames;
    }
}

