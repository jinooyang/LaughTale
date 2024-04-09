package com.jshi.laughtale.wordhistory.repository;

import com.jshi.laughtale.wordhistory.domain.WordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordHistoryRepository extends JpaRepository<WordHistory, Long> {
    List<WordHistory> findByMemberId(Long memberId);

    List<WordHistory> findAllByWordDataIdIn(List<Long> list);

    Optional<WordHistory> findByMemberIdAndWordDataId(Long memberId, Long wordDataId);

    List<WordHistory> findByMemberIdAndStudyCntGreaterThan(Long memberId, int num);
}
