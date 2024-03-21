package com.jshi.laughtale.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.dictionary.domain.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

}
