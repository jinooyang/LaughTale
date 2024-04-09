package com.jshi.laughtale.viewhistory.service;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.chapter.exception.ChapterNotFoundException;
import com.jshi.laughtale.chapter.repository.ChapterRepository;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.repository.MangaRepository;
import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.member.service.MemberService;
import com.jshi.laughtale.viewhistory.exception.AlreadyReadThisChapterException;
import com.jshi.laughtale.viewhistory.mapper.ViewHistoryMapper;
import com.jshi.laughtale.viewhistory.repository.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewHistoryService {

    private final MemberService memberService;
    private final ViewHistoryRepository viewHistoryRepository;
    private final ChapterRepository chapterRepository;
    private final MangaRepository mangaRepository;

    public void createHistory(Long chapterId, Long userId) {
        Member member = memberService.findById(userId);
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(ChapterNotFoundException::new);
        Manga manga = mangaRepository.findByChapter(chapter);

        if (viewHistoryRepository.findViewHistoryByChapterAndMember(chapter, member).isEmpty()) {
            log.info("history 등록 완료 : {}, {}", member.getEmail(), chapter.getId());
            viewHistoryRepository.save(ViewHistoryMapper.toEntity(member, manga, chapter));
        } else {
            throw new AlreadyReadThisChapterException();
        }
    }

    public List<Long> getMangaChapterViewHistory(long memberId, long mangaId) {
        return
                viewHistoryRepository.findChaptersByMemberAndManga(memberId, mangaId);
    }

}
