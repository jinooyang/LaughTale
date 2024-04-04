package com.jshi.laughtale.member.exception;


import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class PasswordNotMatchesException extends BaseException {
    public PasswordNotMatchesException() {
        super(ErrorCode.PASSWORD_NOT_MATCHES);
    }
}
