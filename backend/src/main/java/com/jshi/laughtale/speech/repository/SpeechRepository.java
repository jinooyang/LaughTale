package com.jshi.laughtale.speech.repository;

import com.jshi.laughtale.speech.domain.Speech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeechRepository extends JpaRepository<Speech, Long> {
}
