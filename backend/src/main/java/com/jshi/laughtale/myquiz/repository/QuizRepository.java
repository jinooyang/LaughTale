package com.jshi.laughtale.myquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.myquiz.domain.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
