package com.jshi.laughtale.speech.mapper;

import com.jshi.laughtale.cut.domain.Cut;
import com.jshi.laughtale.cut.dto.CutBasic;
import com.jshi.laughtale.position.domain.Position;
import com.jshi.laughtale.position.dto.PositionBasic;
import com.jshi.laughtale.position.mapper.PositionMapper;
import com.jshi.laughtale.speech.domain.Speech;
import com.jshi.laughtale.speech.dto.SpeechBasic;
import com.jshi.laughtale.speech.dto.SpeechDetail;

import java.util.ArrayList;

public class SpeechMapper {

    public static Speech toEntity(String sentence, int speechNo, Position position) {
        return Speech.builder()
                .sentence(sentence)
                .speechNo(speechNo)
                .position(position)
                .build();
    }

    public static Speech toEntity(Cut cut, String sentence, int speechNo, Position position) {
        return Speech.builder()
                .cut(cut)
                .sentence(sentence)
                .speechNo(speechNo)
                .position(position)
                .wordLists(new ArrayList<>())
                .build();
    }

    public static SpeechBasic.Response toBasicResponse(Speech speech) {
        return SpeechBasic.Response.builder()
                .id(speech.getId())
                .imageUrl(speech.getCut().getImageUrl())
                .position(PositionMapper.toBasicResponse(speech.getPosition()))
                .sentence(speech.getSentence())
                .build();
    }

    public static SpeechDetail.Response toDetailResponse(
            String title, Integer chapterNo, CutBasic.Response cut, PositionBasic.Response positionBasic) {
        return SpeechDetail.Response.builder()
                .positionBasic(positionBasic)
                .title(title)
                .chapterNo(chapterNo)
                .cut(cut)
                .build();
    }

    public static SpeechDetail.Response toAnalyzeResponse(Speech speech) {
        return SpeechDetail.Response.builder()
                .sentence(speech.getSentence())
                .positionBasic(PositionMapper.toBasicResponse(speech.getPosition()))
                .build();
    }
}
