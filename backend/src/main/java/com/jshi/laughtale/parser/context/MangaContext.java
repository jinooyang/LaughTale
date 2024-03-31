package com.jshi.laughtale.parser.context;

import com.jshi.laughtale.manga.domain.Manga;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class MangaContext {
    private Manga manga;
    @Setter
    private List<ChapterContext> chapterContexts;


}
