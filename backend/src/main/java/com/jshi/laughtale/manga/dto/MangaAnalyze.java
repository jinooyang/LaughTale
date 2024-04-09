package com.jshi.laughtale.manga.dto;

import com.jshi.laughtale.chapter.dto.ChapterAnalyze;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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

    @Getter
    public static class Response {
        private String title;
        private String author;
        private String category;
        private String description;
        private String thumbnail;
        @Setter
        private List<ChapterAnalyze.Response> chapter;

        @Builder
        public Response(String title, String author, String thumbnail, String category, String description) {
            this.title = title;
            this.author = author;
            this.category = category;
            this.description = description;
            this.thumbnail = thumbnail;
        }
    }
}

