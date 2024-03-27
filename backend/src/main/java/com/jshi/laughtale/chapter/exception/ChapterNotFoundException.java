package com.jshi.laughtale.chapter.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class ChapterNotFoundException extends BaseException {

    public ChapterNotFoundException() {
        super(ErrorCode.CHAPTER_NOT_FOUND);
    }
}
