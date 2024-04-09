package com.jshi.laughtale.cut.dto;

import com.jshi.laughtale.speech.dto.SpeechDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CutAnalyze {

    @Getter
    public static class Response {
        private String imageUrl;
        private Integer width;
        private Integer height;
        @Setter
        private List<SpeechDetail.Response> sentence;

        @Builder
        public Response(String imageUrl, Integer width, Integer height) {
            this.imageUrl = imageUrl;
            this.width = width;
            this.height = height;
        }
    }
}
