package com.jshi.laughtale.wordlist.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class NotExistWordListException extends BaseException {
    public NotExistWordListException() {
        super(ErrorCode.NOT_EXIST_WORD_LIST);
    }
}
