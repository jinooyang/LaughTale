package com.jshi.laughtale.Dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshi.laughtale.Dictionary.domain.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

}
