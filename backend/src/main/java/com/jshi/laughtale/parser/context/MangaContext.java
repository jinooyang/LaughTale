package com.jshi.laughtale.parser.context;

import com.jshi.laughtale.manga.domain.Manga;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class MangaContext {
    private Manga manga;
    private List<ChapterContext> chapterContexts;


}
