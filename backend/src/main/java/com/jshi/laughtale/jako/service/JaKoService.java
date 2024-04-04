package com.jshi.laughtale.jako.service;

import com.jshi.laughtale.jako.domain.JaKo;
import com.jshi.laughtale.jako.repository.JaKoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JaKoService {

    private final JaKoRepository jaKoRepository;

    public String loadWordMeaning(String from) {
        return jaKoRepository.findByLangFrom(from).map(JaKo::getParsedDef).orElse(null);
    }
}
