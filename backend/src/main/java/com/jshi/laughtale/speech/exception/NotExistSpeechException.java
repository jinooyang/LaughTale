package com.jshi.laughtale.speech.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class NotExistSpeechException extends BaseException {
    public NotExistSpeechException() {
        super(ErrorCode.NOT_EXIST_SPEECH);
    }
}
