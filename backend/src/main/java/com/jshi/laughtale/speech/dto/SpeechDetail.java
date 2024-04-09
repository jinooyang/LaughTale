package com.jshi.laughtale.speech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jshi.laughtale.cut.dto.CutBasic;
import com.jshi.laughtale.position.dto.PositionBasic;
import com.jshi.laughtale.worddata.dto.WordDataDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SpeechDetail {

    @Getter
    @Builder
    @JsonInclude(Include.NON_NULL)
    public static class Response {
        private String title;
        private String sentence;
        private Integer chapterNo;
        private CutBasic.Response cut;
        private PositionBasic.Response positionBasic;
        @Setter
        private List<WordDataDetail.Response> words;
    }
}
