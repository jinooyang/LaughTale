package com.jshi.laughtale.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.quiz.domain.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
	List<Quiz> findByMemberIdOrderByProblemNoAsc(Long memberId);

}
