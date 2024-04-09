package com.jshi.laughtale.parser;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.chapter.mapper.ChapterMapper;
import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.cut.mapper.CutMapper;
import com.jshi.laughtale.manga.domain.Manga;
import com.jshi.laughtale.manga.mapper.MangaMapper;
import com.jshi.laughtale.position.mapper.PositionMapper;
import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.speech.mapper.SpeechMapper;
import com.jshi.laughtale.worddata.domain.WordData;
import com.jshi.laughtale.worddata.mapper.WordDataMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MangaParser {
    private static final String PREFIX = "https://j10a705.p.ssafy.io/";

    public static Manga parseManga(Map attr) {
        List<Object> list = new ArrayList<>(attr.keySet());
        if (list.size() > 1) {
            throw new IllegalArgumentException();
        }
        String title = (String) list.get(0);
        attr = (Map<String, Object>) attr.get(title);
        String description = (String) attr.get(Attribute.DESCRIPTION.toString());
        String author = (String) attr.get(Attribute.AUTHOR.toString());
        String genres = (String) attr.get(Attribute.GENRES.toString());
        String thumbnail = attachPrefix((String) attr.get(Attribute.THUMBNAIL.toString()));
        return MangaMapper.toEntity(title, author, description, genres, thumbnail);
    }

    public static Chapter parseChapter(int chapterNo, int pageCnt) {
        return ChapterMapper.toEntity(chapterNo, pageCnt);
    }

    public static Cut parseCut(Map<String, Object> attr) {
        int page = (Integer) attr.get(Attribute.PAGE.toString());
        String imageUrl = attachPrefix((String) attr.get(Attribute.SRC.toString()));
        List<Integer> size = Optional.ofNullable((List<Integer>) attr.get(Attribute.SIZE.toString())).orElse(List.of(-1, -1));
        return CutMapper.toEntity(page, imageUrl, size);
    }

    public static Speech parseSpeech(Map<String, Object> attr) {
        int idx = Integer.parseInt((String) attr.get(Attribute.IDX.toString()));
        List<Integer> pos = ((List<String>) attr.get(Attribute.POS.toString())).stream().map(Integer::parseInt).toList();
        String sentence = (String) attr.get(Attribute.SENTENCE.toString());
        return SpeechMapper.toEntity(sentence, idx, PositionMapper.listToPosition(pos));
    }

    public static WordData parseWordData(Map<String, Object> attr) {
        String word = (String) attr.get(Attribute.VALUE.toString());
        String partOfSpeech = (String) attr.get(Attribute.PO_SPEECH.toString());
        return WordDataMapper.toEntity(word, partOfSpeech);
    }

    private static String attachPrefix(String src) {
        if (src.startsWith("/")) {
            return PREFIX + src.substring(1);
        }
        return PREFIX + src;
    }
}
