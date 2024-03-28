package com.jshi.laughtale.jako.repository;

import com.jshi.laughtale.jako.domain.JaKo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JaKoRepository extends JpaRepository<JaKo, Integer> {
    Optional<JaKo> findByLangFrom(String word);
}
