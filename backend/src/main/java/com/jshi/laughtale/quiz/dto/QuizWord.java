package com.jshi.laughtale.quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jshi.laughtale.position.domain.Position;
import lombok.*;

import java.util.List;

/*
 * 프론트엔드에서 문제를 보여주기 위해 필요한 데이터는 다음과 같다
 * 이미지 주소(ㅇ)
 * 이미지 내에서 말풍선 좌표(ㅇ)
 * 1번보기 ~ 4번보기에 해당하는 String값(ㅇ)
 * 정답번호(ㅇ)
 * 정답의 뜻(문제에 넣기위함)(ㅇ)
 * 단어의 난이도(ㅇ)
 * 단어를 단어장에 추가하고 싶을 때를 위한 wordListId(X)
 * @JsonIgnore는 문제를 생성하기 위해 필요한 변수
 * 퀴즈 PK도 있어야한다(X)
 * */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuizWord {
    //정답이 등장하는 말풍선 좌표
    @JsonIgnore
    private Long speechId;
    //정답에 해당하는 단어 아이디
    @JsonIgnore
    private Long wordDataId;
    //정답에 해당하는 단어
    @JsonIgnore
    private String answerWord;
    @JsonIgnore
    private int weight;
    @JsonIgnore
    private List<String> optionSet;

    //단어가 등장한 wordListId
    private Long wordListId;
    //단어의 난이도
    private Integer level;
    //단어가 등장한 말풍선의 문장
    private String sentence;
    //이미지 URL
    private String imageUrl;
    //정답 번호
    private Integer answerNo;
    //정답 단어의 뜻
    private String definition;

    //이미지의 크기
    private Integer width;
    private Integer height;

    // 1번보기 ~ 4번보기에 해당하는 String값(X)
    private String[] option;
    //정답에 해당하는 단어가 나오는 말풍선의 좌표
    private Integer leftTopX;
    private Integer leftTopY;

    private Integer rightTopX;
    private Integer rightTopY;

    private Integer leftBottomX;
    private Integer leftBottomY;

    private Integer rightBottomX;
    private Integer rightBottomY;

    public QuizWord(Long speechId, Long wordDataId, String answerWord, Integer level, Long wordListId) {
        super();
        this.speechId = speechId;
        this.wordDataId = wordDataId;
        this.answerWord = answerWord;
        this.level = level;
        this.wordListId = wordListId;
    }

    public void addPositionToQuizWord(Position position) {
        this.setLeftTopX(position.getLeftTopX());
        this.setLeftTopY(position.getLeftTopY());

        this.setRightTopX(position.getRightTopX());
        this.setRightTopY(position.getRightTopY());

        this.setLeftBottomX(position.getLeftBottomX());
        this.setLeftBottomY(position.getLeftBottomY());

        this.setRightBottomX(position.getRightBottomX());
        this.setRightBottomY(position.getRightBottomY());

    }


}
