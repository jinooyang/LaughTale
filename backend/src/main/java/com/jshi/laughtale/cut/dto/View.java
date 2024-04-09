package com.jshi.laughtale.cut.dto;

import com.jshi.laughtale.position.domain.Position;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class View {
    @Getter
    @Builder
    public static class CutSpeech {
        private Long id;
        private String sentence;
        private Position position;

    }

    @Getter
    public static class Request {

        private Long chapterId;
        private Integer page;
        private Integer size;
    }

    @Getter
    @Builder
    public static class Response {
        private String imageUrl;
        private Integer width;
        private Integer height;
        private List<CutSpeech> speeches;
    }

}
