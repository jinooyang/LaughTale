package com.jshi.laughtale.cut.mapper;

import com.jshi.laughtale.chapter.domain.Chapter;
import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.cut.dto.CutAnalyze;
import com.jshi.laughtale.cut.dto.CutBasic;
import com.jshi.laughtale.cut.dto.View;
import com.jshi.laughtale.speech.domain.Speech;

import java.util.ArrayList;
import java.util.List;

public class CutMapper {

    public static Cut toEntity(int page, String imageUrl, List<Integer> size) {
        return Cut.builder()
                .cutNo(page)
                .imageUrl(imageUrl)
                .width(size.get(0))
                .height(size.get(1))
                .build();
    }

    public static Cut toEntity(Chapter chapter, int cutNo, String imageUrl, List<Integer> size) {
        return Cut.builder()
                .cutNo(cutNo)
                .imageUrl(imageUrl)
                .chapter(chapter)
                .width(size.get(0))
                .height(size.get(1))
                .speeches(new ArrayList<>())
                .build();
    }

    public static CutAnalyze.Response toAnalyzeResponse(Cut cut) {
        return CutAnalyze.Response.builder()
                .imageUrl(cut.getImageUrl())
                .width(cut.getWidth())
                .height(cut.getHeight())
                .build();
    }

    public static CutBasic.Response toBasicResponse(Cut cut) {
        return CutBasic.Response.builder()
                .height(cut.getHeight())
                .width(cut.getWidth())
                .imageUrl(cut.getImageUrl())
                .build();
    }

    public static View.Response toChapterImage(Cut cut) {
        List<View.CutSpeech> speeches = new ArrayList<>();
        List<Speech> speechList = cut.getSpeeches();
        for (Speech speech : speechList) {
            View.CutSpeech cutSpeech = View.CutSpeech.builder()
                    .id(speech.getId())
                    .sentence(speech.getSentence())
                    .position(speech.getPosition())
                    .build();

            speeches.add(cutSpeech);
        }
        return View.Response.builder()
                .width(cut.getWidth())
                .height(cut.getHeight())
                .imageUrl(cut.getImageUrl())
                .speeches(speeches)
                .build();
    }
}
