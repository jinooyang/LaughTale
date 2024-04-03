package com.jshi.laughtale.viewhistory.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class AlreadyReadThisChapterException extends BaseException {
    public AlreadyReadThisChapterException() {
        super(ErrorCode.ALREADY_READ_THIS_CHAPTER);
    }
}
