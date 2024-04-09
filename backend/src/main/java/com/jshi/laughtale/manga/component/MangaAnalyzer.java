package com.jshi.laughtale.manga.component;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.chapter.dto.ChapterAnalyze;
import com.jshi.laughtale.chapter.mapper.ChapterMapper;
import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.cut.dto.CutAnalyze;
import com.jshi.laughtale.cut.mapper.CutMapper;
import com.jshi.laughtale.jako.service.JaKoService;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.dto.MangaAnalyze;
import com.jshi.laughtale.manga.mapper.MangaMapper;
import com.jshi.laughtale.parser.context.ChapterContext;
import com.jshi.laughtale.parser.context.CutContext;
import com.jshi.laughtale.parser.context.MangaContext;
import com.jshi.laughtale.parser.context.SpeechContext;
import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.speech.dto.SpeechDetail;
import com.jshi.laughtale.speech.mapper.SpeechMapper;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.worddata.dto.WordDataDetail;
import com.jshi.laughtale.worddata.dto.WordDataDetail.Response;
import com.jshi.laughtale.worddata.mapper.WordDataMapper;
import com.jshi.laughtale.worddata.service.WordDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class MangaAnalyzer {
    private final JaKoService jaKoService;
    private final WordDataService wordDataService;

    public MangaAnalyze.Response analyze(MangaContext mangaContext, int last) {
        Manga manga = mangaContext.getManga();
        MangaAnalyze.Response response = MangaMapper.toAnalyzeResponse(manga);
        response.setChapter(analyzeChapter(manga, mangaContext.getChapterContexts(), last));
        return response;
    }

    private List<ChapterAnalyze.Response> analyzeChapter(Manga manga, List<ChapterContext> chapterContexts, int last) {
        List<Chapter> chapters = Optional.ofNullable(manga.getChapter()).orElse(new ArrayList<>());
        List<ChapterAnalyze.Response> chapterResponse = new ArrayList<>();
        int idx = chapters.size();
        for (int i = 0; i < chapterContexts.size(); i++) {
            ChapterContext chapterContext = chapterContexts.get(i);
            Chapter chapter = chapterContext.getChapter();
            chapter.setManga(manga);
            chapter.setChapterNo(chapter.getChapterNo() + last);
            ChapterAnalyze.Response analyzeResponse = ChapterMapper.toAnalyzeResponse(chapter);

            List<CutAnalyze.Response> cutAnalyze = analyzeCut(chapter, chapterContext.getCutContexts());
            analyzeResponse.setCuts(cutAnalyze);
            chapters.add(chapter);
            chapterResponse.add(analyzeResponse);
        }
        chapterResponse.forEach(c -> c.setLevel(calc(c)));


        for (int i = 0; i < chapterResponse.size(); i++) {
            chapters.get(idx + i).setLevel(chapterResponse.get(i).getLevel());
        }
        manga.updateChapter(chapters);
        return chapterResponse;
    }

    private List<CutAnalyze.Response> analyzeCut(Chapter chapter, List<CutContext> cutContexts) {
        List<Cut> cuts = new ArrayList<>();
        List<CutAnalyze.Response> cutAnalyze = new ArrayList<>();
        for (int i = 0; i < cutContexts.size(); i++) {
            CutContext cutContext = cutContexts.get(i);
            Cut cut = cutContext.getCut();
            cut.setChapter(chapter);
            CutAnalyze.Response analyzeResponse = CutMapper.toAnalyzeResponse(cut);

            List<SpeechDetail.Response> speechAnalyze = analyzeSpeech(cut, cutContext.getSpeechContexts());
            analyzeResponse.setSentence(speechAnalyze);
            cuts.add(cut);
            cutAnalyze.add(analyzeResponse);
        }
        chapter.setCuts(cuts);
        return cutAnalyze;
    }

    private List<SpeechDetail.Response> analyzeSpeech(Cut cut, List<SpeechContext> speechContexts) {
        List<Speech> speeches = new ArrayList<>();
        List<SpeechDetail.Response> speechResponses = new ArrayList<>();
        for (int i = 0; i < speechContexts.size(); i++) {
            SpeechContext speechContext = speechContexts.get(i);
            Speech speech = speechContext.getSpeech();
            speech.setCut(cut);

            SpeechDetail.Response speechResponse = SpeechMapper.toAnalyzeResponse(speech);
            List<WordDataDetail.Response> wordDataResponse = analyzeWordData(speechContext.getWordDataList());

            speechResponse.setWords(wordDataResponse);
            speechResponses.add(speechResponse);
            speeches.add(speech);
        }
        cut.setSpeeches(speeches);
        return speechResponses;
    }

    private List<WordDataDetail.Response> analyzeWordData(List<WordData> wordDataList) {
        List<WordDataDetail.Response> wordDataResponse = new ArrayList<>();
        for (int i = 0; i < wordDataList.size(); i++) {
            WordData wordData = wordDataList.get(i);
            String def = jaKoService.loadWordMeaning(wordData.getWord());
            Integer level = wordDataService.loadLevelByWord(wordData.getWord());
            if (def == null || level == null) {
                continue;
            }
            wordData.setDefinition(def);
            wordData.setLevel(level);
            wordDataResponse.add(WordDataMapper.toDetailResponse(wordData));
        }
        return wordDataResponse;
    }

    private int calc(ChapterAnalyze.Response chapter) {
        Map<Integer, Long> countByLevel = chapter.getCuts().stream()
                .flatMap(cut -> cut.getSentence().stream())
                .flatMap(response -> response.getWords().stream())
                .collect(Collectors.groupingBy(Response::getLevel, Collectors.counting()));
        return calculateChapterLevel(countByLevel);
    }

    private int calculateChapterLevel(Map<Integer, Long> countByLevel) {
        long totalSum = 0;
        long totalCnt = 0;
        for (Integer level : countByLevel.keySet()) {
            long cnt = countByLevel.getOrDefault(level, 0L);
            totalSum += level * cnt;
            totalCnt += cnt;
        }
        double avg = (double) totalSum / totalCnt;
        return averageToLevel(avg);
    }

    public int averageToLevel(double avg) {
        log.info("avg : {}", avg);
        if (avg <= 1.67) {
            return 1;
        }
        if (avg <= 1.73) {
            return 2;
        }
        if (avg <= 1.8) {
            return 3;
        }
        if (avg <= 1.9) {
            return 4;
        }
        return 5;
    }
}
