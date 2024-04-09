package com.jshi.laughtale.worddata.repository;


import com.jshi.laughtale.worddata.domain.WordData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WordDataRepository extends JpaRepository<WordData, Long> {
    Optional<WordData> findByWord(String word);

    Page<WordData> findAllByOrderByFrequencyDesc(Pageable pageable);

    @Query("SELECT w.level FROM WordData w WHERE w.word = :word")
    Optional<Integer> findLevelByWord(String word);

    @Query("SELECT w FROM WordData w WHERE w.definition IS NOT NULL ORDER BY rand() LIMIT 5")
    List<WordData> findRandom();
}
