package com.jshi.laughtale.worddata.repository;

import com.jshi.laughtale.worddata.domain.WordData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordDataRepository extends JpaRepository<WordData, Long> {

    Optional<WordData> findByWord(String word);
}
