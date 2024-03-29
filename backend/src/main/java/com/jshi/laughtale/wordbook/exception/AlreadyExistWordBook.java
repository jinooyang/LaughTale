package com.jshi.laughtale.wordbook.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class AlreadyExistWordBook extends BaseException {
    public AlreadyExistWordBook() {
        super(ErrorCode.ALREADY_EXIST_WORD_BOOK);
    }
}
