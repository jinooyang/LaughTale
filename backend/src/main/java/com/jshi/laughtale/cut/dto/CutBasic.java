package com.jshi.laughtale.cut.dto;

import lombok.Builder;
import lombok.Getter;

public class CutBasic {

    @Builder
    @Getter
    public static class Response {
        private String imageUrl;
        private Integer width;
        private Integer height;
    }
}
