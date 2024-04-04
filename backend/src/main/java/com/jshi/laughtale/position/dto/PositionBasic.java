package com.jshi.laughtale.position.dto;

import lombok.Builder;
import lombok.Getter;

public class PositionBasic {

    @Builder
    @Getter
    public static class Response {
        private Integer leftTopX;
        private Integer leftTopY;

        private Integer rightTopX;
        private Integer rightTopY;

        private Integer leftBottomX;
        private Integer leftBottomY;

        private Integer rightBottomX;
        private Integer rightBottomY;
    }
}
