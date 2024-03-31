package com.jshi.laughtale.wordbook.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class NotExistWordBookException extends BaseException {
    public NotExistWordBookException() {
        super(ErrorCode.NOT_EXIST_WORD_BOOK);
    }
}
