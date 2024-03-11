package com.jshi.laughtale.common;

public class BaseException extends RuntimeException{
    ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
