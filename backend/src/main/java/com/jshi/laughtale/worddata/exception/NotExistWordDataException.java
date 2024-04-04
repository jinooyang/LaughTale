package com.jshi.laughtale.worddata.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class NotExistWordDataException extends BaseException {
    public NotExistWordDataException() {
        super(ErrorCode.NOT_EXIST_WORD_DATA);
    }
}
