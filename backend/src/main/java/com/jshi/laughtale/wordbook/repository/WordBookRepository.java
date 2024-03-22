package com.jshi.laughtale.wordbook.repository;

import com.jshi.laughtale.wordbook.domain.WordBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordBookRepository extends JpaRepository<WordBook, Long> {

    @Query("SELECT WordBook FROM WordBook as w JOIN WordData as d ON d.level = :level WHERE w.member.id = :memberId")
    List<WordBook> findAllByMemberIdWithLevel(int level, long memberId);
}
