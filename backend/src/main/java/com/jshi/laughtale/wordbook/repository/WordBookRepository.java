package com.jshi.laughtale.wordbook.repository;

import com.jshi.laughtale.wordbook.domain.WordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordBookRepository extends JpaRepository<WordBook, Long> {
}
