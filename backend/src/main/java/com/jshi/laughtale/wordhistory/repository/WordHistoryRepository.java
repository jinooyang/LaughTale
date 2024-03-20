package com.jshi.laughtale.wordhistory.repository;

import com.jshi.laughtale.wordhistory.domain.WordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordHistoryRepository extends JpaRepository<WordHistory, Long> {
	List<WordHistory> findByMemberId(Long memberId);

	List<WordHistory> findByWordDataIn(List<Long> list);
}
