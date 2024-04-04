package com.jshi.laughtale.quiz.repository;

import com.jshi.laughtale.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByMemberIdOrderByProblemNoAsc(Long memberId);

}
