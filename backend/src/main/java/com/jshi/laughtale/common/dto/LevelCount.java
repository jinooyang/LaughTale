package com.jshi.laughtale.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class LevelCount {
    @Getter
    @Builder
    @Setter
    public static class Response {
        private int level;
        private long count;
    }
}
