package com.jshi.laughtale.cut.service;

import com.jshi.laughtale.chapter.service.ChapterService;
import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.cut.repository.CutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CutService {
    private final CutRepository cutRepository;
    private final ChapterService chapterService;

    public Page<Cut> findCutsByChapter(Long chapterId, int page, int size) {
        return cutRepository.findAllByChapter(chapterService.findById(chapterId), PageRequest.of(page, size));
    }
}
