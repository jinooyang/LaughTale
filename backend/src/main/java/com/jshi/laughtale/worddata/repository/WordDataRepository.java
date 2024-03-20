package com.jshi.laughtale.worddata.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.worddata.domain.WordData;

public interface WordDataRepository extends JpaRepository<WordData, Long> {
	Optional<WordData> findByWord(String word);
}
