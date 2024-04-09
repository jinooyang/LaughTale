package com.jshi.laughtale.chapter.dto;

import com.jshi.laughtale.cut.dto.CutAnalyze;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ChapterAnalyze {

    @Getter
    public static class Response {
        private Integer chapterNo;
        @Setter
        private Integer level;
        @Setter
        private List<CutAnalyze.Response> cuts;

        @Builder
        public Response(Integer chapterNo, Integer level) {
            this.chapterNo = chapterNo;
            this.level = level;
        }
    }
}
