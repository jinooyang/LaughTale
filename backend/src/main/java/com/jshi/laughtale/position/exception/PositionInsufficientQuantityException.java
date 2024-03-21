package com.jshi.laughtale.position.exception;

import com.jshi.laughtale.common.BaseException;
import com.jshi.laughtale.common.ErrorCode;

public class PositionInsufficientQuantityException extends BaseException {
    public PositionInsufficientQuantityException() {
        super(ErrorCode.POSITION_INSUFFICIENT_QUANTITY);
    }
}
