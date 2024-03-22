package com.jshi.laughtale.speech.dto;

import com.jshi.laughtale.cut.dto.CutBasic;
import com.jshi.laughtale.position.dto.PositionBasic;
import lombok.Builder;
import lombok.Getter;

public class SpeechDetail {

    @Getter
    @Builder
    public static class Response {
        private String title;
        private Integer chapterNo;
        private CutBasic.Response cut;
        private PositionBasic positionBasic;
    }
}
