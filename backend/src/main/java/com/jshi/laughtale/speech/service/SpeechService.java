package com.jshi.laughtale.speech.service;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.chapter.mapper.ChapterMapper;
import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.cut.dto.CutBasic;
import com.jshi.laughtale.cut.mapper.CutMapper;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.position.dto.PositionBasic;
import com.jshi.laughtale.position.mapper.PositionMapper;
import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.speech.dto.SpeechBasic;
import com.jshi.laughtale.speech.dto.SpeechDetail;
import com.jshi.laughtale.speech.exception.NotExistSpeechException;
import com.jshi.laughtale.speech.mapper.SpeechMapper;
import com.jshi.laughtale.speech.repository.SpeechRepository;
import com.jshi.laughtale.wordlist.domain.WordList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeechService {
    private final SpeechRepository speechRepository;

    public Speech findById(Long id) {
        return speechRepository.findById(id).orElseThrow();
    }

    public SpeechBasic.Response loadByWordList(WordList wordList) {
        Speech speech = speechRepository.findById(wordList.getSpeech().getId())
                .orElseThrow(NotExistSpeechException::new);
        return SpeechMapper.toBasicResponse(speech);
    }

    public SpeechDetail.Response loadBySpeechDetail(Long id) {
        Speech speech = speechRepository.findById(id).orElseThrow(NotExistSpeechException::new);

        Cut cut = speech.getCut();
        CutBasic.Response cutResponse = CutMapper.toBasicResponse(cut);

        Chapter chapter = cut.getChapter();
        Integer chapterNo = ChapterMapper.toChapterNo(chapter);

        Manga manga = chapter.getManga();
        String title = manga.getTitle();

        PositionBasic.Response positionBasic = PositionMapper.toBasicResponse(speech.getPosition());

        return SpeechMapper.toDetailResponse(title, chapterNo, cutResponse, positionBasic);
    }
}
