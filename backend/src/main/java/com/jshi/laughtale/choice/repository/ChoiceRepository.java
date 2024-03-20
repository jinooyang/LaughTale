package com.jshi.laughtale.choice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.choice.domain.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
