package com.jshi.laughtale.parser.context;

import com.jshi.laughtale.chapter.domain.Chapter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Builder
@Getter
public class ChapterContext {
    private Chapter chapter;
    @Setter
    private List<CutContext> cutContexts;
}
