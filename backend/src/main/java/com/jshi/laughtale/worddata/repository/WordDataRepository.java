package com.jshi.laughtale.worddata.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.worddata.domain.WordData;

public interface WordDataRepository extends JpaRepository<WordData, Long> {
	Optional<WordData> findByWord(String word);

	Page<WordData> findAllByOrderByFrequencyDesc(Pageable pageable);

}
