package com.jshi.laughtale.parser.service;

import com.jshi.laughtale.parser.Attribute;
import com.jshi.laughtale.parser.MangaParser;
import com.jshi.laughtale.parser.context.ChapterContext;
import com.jshi.laughtale.parser.context.CutContext;
import com.jshi.laughtale.parser.context.MangaContext;
import com.jshi.laughtale.parser.context.SpeechContext;
import com.jshi.laughtale.worddata.domain.WordData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ParseService {

    public MangaContext parse(Map attr) {
        MangaContext mangaContext = MangaContext.builder()
                .manga(MangaParser.parseManga(attr))
                .build();
        attr = (Map<String, Object>) attr.get(mangaContext.getManga().getTitle());
        List<List<Object>> chapters = (List<List<Object>>) attr.get(Attribute.CHAPTER.toString());
        List<ChapterContext> chapterContexts = new ArrayList<>();

        for (int i = 0; i < chapters.size(); i++) {
            ChapterContext chapterContext = chapterParsing(i, chapters.get(i));
            chapterContexts.add(chapterContext);
        }
        mangaContext.setChapterContexts(chapterContexts);
        return mangaContext;
    }

    private ChapterContext chapterParsing(int idx, List<Object> chapter) {
        ChapterContext chapterContext = ChapterContext.builder()
                .chapter(MangaParser.parseChapter(idx, chapter.size()))
                .build();
        List<CutContext> cutContexts = new ArrayList<>();
        for (int i = 0; i < chapter.size(); i++) {
            Map<String, Object> cut = (Map<String, Object>) chapter.get(i);
            CutContext cutContext = cutParsing(cut);
            cutContexts.add(cutContext);
        }
        chapterContext.getChapter().setLevel(cutContexts.size());
        chapterContext.setCutContexts(cutContexts);
        return chapterContext;
    }

    private CutContext cutParsing(Map<String, Object> cut) {
        CutContext cutContext = CutContext.builder()
                .cut(MangaParser.parseCut(cut))
                .build();
        List<SpeechContext> speechContexts = new ArrayList<>();
        List<Map<String, Object>> speeches = (List<Map<String, Object>>) cut.get(Attribute.SPEECH.toString());
        for (int i = 0; i < speeches.size(); i++) {
            Map<String, Object> speech = speeches.get(i);
            SpeechContext speechContext = speechParsing(speech);
            speechContexts.add(speechContext);
        }
        cutContext.setSpeechContexts(speechContexts);
        return cutContext;
    }

    private SpeechContext speechParsing(Map<String, Object> speech) {
        SpeechContext speechContext = SpeechContext.builder()
                .speech(MangaParser.parseSpeech(speech))
                .build();
        List<Map<String, Object>> words = (List<Map<String, Object>>) speech.get(Attribute.WORD_LIST.toString());
        List<WordData> wordDataList = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            WordData wordData = wordDataParsing(words.get(i));
            wordDataList.add(wordData);
        }
        speechContext.setWordDataList(wordDataList);
        return speechContext;
    }

    private WordData wordDataParsing(Map<String, Object> word) {
        return MangaParser.parseWordData(word);
    }
}
