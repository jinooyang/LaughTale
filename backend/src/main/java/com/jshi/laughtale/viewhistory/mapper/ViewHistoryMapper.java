package com.jshi.laughtale.viewhistory.mapper;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.viewhistory.domain.ViewHistory;

public class ViewHistoryMapper {

    public static ViewHistory toEntity(Member member, Chapter chapter) {
        return ViewHistory.builder()
                .member(member)
                .chapter(chapter)
                .build();
    }
}
