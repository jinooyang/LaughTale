package com.jshi.laughtale.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(BAD_REQUEST, "MEMBER_NOT_FOUND"),
    PASSWORD_NOT_MATCHES(BAD_REQUEST, "PASSWORD NOT MATCH"),
    ALREADY_READ_THIS_CHAPTER(BAD_REQUEST, "ALREADY READ THIS CHAPTER"),

    FILE_NOT_FOUND(BAD_REQUEST, "FILE_NOT_FOUND"),

    NOT_EXIST_WORD_DATA(BAD_REQUEST, "NOT_EXIST_WORD_DATA"),

    NOT_EXIST_WORD_LIST(BAD_REQUEST, "NOT_EXIST_WORD_LIST"),
    ALREADY_EXIST_WORD_BOOK(BAD_REQUEST, "ALREADY_EXIST_WORD_BOOK"),

    NOT_EXIST_SPEECH(BAD_REQUEST, "NOT_EXIST_SPEECH"),
    NOT_EXIST_WORD_BOOK(BAD_REQUEST, "NOT_EXIST_WORD_BOOK"),

    CHAPTER_NOT_FOUND(BAD_REQUEST, "CHAPTER_NOT_FOUND"),

    POSITION_INSUFFICIENT_QUANTITY(BAD_REQUEST, "INSUFFICIENT_QUANTITY");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
