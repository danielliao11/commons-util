package com.saintdan.util.commons.exception;

import com.saintdan.util.commons.enums.ErrorType;

/**
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 8/30/15
 * @since JDK1.8
 */
public class SignatureException extends SystemException {

    private final static ErrorType ERROR_TYPE = ErrorType.SGN0001;

    public SignatureException(ErrorType msg, Throwable t) {
        super(msg, t);
    }

    public SignatureException(ErrorType msg) {
        super(msg);
    }

    public SignatureException() {
        super(ERROR_TYPE);
    }
}
