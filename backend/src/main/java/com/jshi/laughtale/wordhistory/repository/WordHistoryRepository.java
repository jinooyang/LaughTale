package com.jshi.laughtale.wordhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.wordhistory.domain.WordHistory;

public interface WordHistoryRepository extends JpaRepository<WordHistory, Long> {
	List<WordHistory> findByMemberId(Long memberId);

	List<WordHistory> findByWordIdIn(List<Long> list);
}
