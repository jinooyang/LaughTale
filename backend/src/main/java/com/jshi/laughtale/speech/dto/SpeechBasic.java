package com.jshi.laughtale.speech.dto;

import com.jshi.laughtale.position.dto.PositionBasic;
import lombok.Builder;
import lombok.Getter;

public class SpeechBasic {

    @Builder
    @Getter
    public static class Response {
        private Long id;
        private String sentence;
        private PositionBasic.Response position;
        private String imageUrl;
    }
}
