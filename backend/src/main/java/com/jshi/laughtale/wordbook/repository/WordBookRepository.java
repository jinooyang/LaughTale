package com.jshi.laughtale.wordbook.repository;

import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.wordbook.domain.WordBook;
import com.jshi.laughtale.worddata.domain.WordData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WordBookRepository extends JpaRepository<WordBook, Long> {

    @Query("SELECT w FROM WordBook as w JOIN WordData as d ON w.wordData = d WHERE w.member.id = :memberId AND d.level = :level")
    Page<WordBook> findAllByMemberIdWithLevel(int level, long memberId, Pageable pageable);

    @Query("SELECT w FROM WordBook as w WHERE w.wordData.id = :wordDataId AND w.member.id = :memberId")
    Optional<WordBook> findByWordDataId(Long wordDataId, Long memberId);

    Optional<WordBook> findByMemberAndWordData(Member member, WordData wordData);
}
