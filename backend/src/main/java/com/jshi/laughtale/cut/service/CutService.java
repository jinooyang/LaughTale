package com.jshi.laughtale.cut.service;

import com.jshi.laughtale.cut.repository.CutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
@RequiredArgsConstructor
public class CutService {

    private final CutRepository cutRepository;
}
