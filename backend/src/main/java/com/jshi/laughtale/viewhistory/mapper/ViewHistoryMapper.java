package com.jshi.laughtale.viewhistory.mapper;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.viewhistory.domain.ViewHistory;

import java.time.LocalDateTime;

public class ViewHistoryMapper {

    public static ViewHistory toEntity(Member member, Manga manga, Chapter chapter) {
        return ViewHistory.builder()
                .member(member)
                .manga(manga)
                .chapter(chapter)
                .viewDate(LocalDateTime.now())
                .build();
    }
}
