package com.jshi.laughtale.manga.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MangaUpload {

    @Getter
    @Builder
    @ToString
    public static class Request {
        private String title;
        private String author;
        private String genres;
        private String description;
    }
}
