package com.jshi.laughtale.parser;

public enum Attribute {
    //만화
    DESCRIPTION, GENRES, AUTHOR, THUMBNAIL, CHAPTER,
    //컷
    PAGE, SPEECH, SRC, SIZE,
    //말풍선
    IDX, POS, SENTENCE, WORD_LIST,
    //단어
    PO_SPEECH, VALUE;


    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
