package com.jshi.laughtale.viewhistory.repository;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.viewhistory.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

    Optional<ViewHistory> findByMemberAndChapter(Member member, Chapter chapter);

    Optional<List<ViewHistory>> findAllByMemberAndChapter(Member member, Chapter chapter);


    @Query(value = "SELECT vh.chapter.id FROM ViewHistory vh WHERE vh.member.id = :memberId AND vh.chapter.manga.id = :mangaId")
    List<Long> findChaptersByMemberAndManga(long memberId, long mangaId);

    Optional<ViewHistory> findViewHistoryByChapterAndMember(Chapter chapter, Member member);
}
