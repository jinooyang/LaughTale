package com.jshi.laughtale.common;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(BAD_REQUEST, "MEMBER_NOT_FOUND"),
    PASSWORD_NOT_MATCHES(BAD_REQUEST, "PASSWORD NOT MATCH");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
