package com.jshi.laughtale.manga.component;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.chapter.repository.ChapterRepository;
import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.cut.repository.CutRepository;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.repository.MangaRepository;
import com.jshi.laughtale.parser.context.ChapterContext;
import com.jshi.laughtale.parser.context.CutContext;
import com.jshi.laughtale.parser.context.MangaContext;
import com.jshi.laughtale.parser.context.SpeechContext;
import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.speech.repository.SpeechRepository;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.worddata.repository.WordDataRepository;
import com.jshi.laughtale.wordlist.domain.WordList;
import com.jshi.laughtale.wordlist.mapper.WordListMapper;
import com.jshi.laughtale.wordlist.repository.WordListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MangaSaver {
    private final MangaRepository mangaRepository;
    private final ChapterRepository chapterRepository;
    private final CutRepository cutRepository;
    private final SpeechRepository speechRepository;
    private final WordDataRepository wordDataRepository;
    private final WordListRepository wordListRepository;

    public void save(MangaContext mangaContext) {
        Manga manga = mangaContext.getManga();

        mangaRepository.save(manga);
        saveChapter(mangaContext.getChapterContexts());
    }

    private void saveChapter(List<ChapterContext> chapterContexts) {
        for (int i = 0; i < chapterContexts.size(); i++) {
            ChapterContext chapterContext = chapterContexts.get(i);
            Chapter chapter = chapterContext.getChapter();
            chapterRepository.save(chapter);

            saveCut(chapterContext.getCutContexts());
        }
    }

    private void saveCut(List<CutContext> cutContexts) {
        for (int i = 0; i < cutContexts.size(); i++) {
            CutContext cutContext = cutContexts.get(i);
            Cut cut = cutContext.getCut();
            cutRepository.save(cut);

            saveSpeech(cutContext.getSpeechContexts());
        }
    }

    private void saveSpeech(List<SpeechContext> speechContexts) {
        for (int i = 0; i < speechContexts.size(); i++) {
            SpeechContext speechContext = speechContexts.get(i);
            Speech speech = speechContext.getSpeech();
            speechRepository.save(speech);

            saveWordData(speech, speechContext.getWordDataList());
        }
    }

    private void saveWordData(Speech speech, List<WordData> wordDataList) {
        List<WordList> wordLists = new ArrayList<>();
        for (int i = 0; i < wordDataList.size(); i++) {
            WordData wordData = wordDataList.get(i);
            WordData findWord = wordDataRepository.findByWord(wordData.getWord()).orElse(null);
            if (findWord != null) {
                wordData = findWord;
            }
            wordData.updateFrequency();
            wordDataRepository.save(wordData);

            WordList wordList = WordListMapper.toEntity(speech, wordData);
            wordListRepository.save(wordList);
            wordLists.add(wordList);
        }
        speech.setWordLists(wordLists);
    }
}
