package com.jshi.laughtale.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempDTO {
    private Long wordDataId;
    private String answerWord;
    private Integer level;
    private Long wordListId;
    private Long speechId;

    // Constructor
    public TempDTO(Long wordDataId, String answerWord, Integer level, Long wordListId, Long speechId) {
        this.wordDataId = wordDataId;
        this.answerWord = answerWord;
        this.level = level;
        this.wordListId = wordListId;
        this.speechId = speechId;
    }

}
