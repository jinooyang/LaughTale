package com.jshi.laughtale.manga.mapper;

import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.dto.MangaAnalyze;
import com.jshi.laughtale.manga.dto.MangaUpload;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MangaMapper {

    public static final String EMPTY = "EMPTY";

    public static Manga toEntity(String title, String author, String description, String genres, String thumbnail) {
        return Manga.builder()
                .title(title)
                .author(author)
                .description(description)
                .category(genres)
                .thumbnail(thumbnail)
                .build();
    }

    public static MangaUpload.Request emptyUploadRequest() {
        return MangaUpload.Request.builder()
                .title(EMPTY + "/" + randomId())
                .genres(EMPTY)
                .description(EMPTY)
                .author(EMPTY)
                .build();
    }

    private static String randomId() {
        return UUID.randomUUID().toString();
    }

    public static MangaAnalyze.Request toAnalyze(
            String thumbnailPath,
            MangaUpload.Request upload,
            Integer chapterNo,
            List<String> filenames
    ) {
        return MangaAnalyze.Request.builder()
                .title(upload.getTitle())
                .thumbnail(thumbnailPath)
                .chapterNo(chapterNo)
                .author(upload.getAuthor())
                .category(upload.getGenres())
                .description(upload.getDescription())
                .filenames(filenames)
                .build();
    }

    public static MangaAnalyze.Response toAnalyzeResponse(Manga manga) {
        return MangaAnalyze.Response.builder()
                .title(manga.getTitle())
                .description(manga.getDescription())
                .author(manga.getAuthor())
                .category(manga.getCategory())
                .thumbnail(manga.getThumbnail())
                .build();
    }

    public static Manga analyzeToEntity(MangaAnalyze.Request request) {
        return Manga.builder()
                .title(request.getTitle())
                .chapterCnt(1)
                .author(request.getAuthor())
                .category(request.getCategory())
                .description(request.getDescription())
                .level(0)
                .chapter(new ArrayList<>())
                .build();
    }
}
