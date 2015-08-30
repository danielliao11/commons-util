package com.saintdan.util.commons.enums;

/**
 * Error type enums.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 8/30/15
 * @since JDK1.8
 */
public enum ErrorType implements IntentState {

    // System
    SYS0001("System error."),

    // Signature
    SGN0001("Signature error."),

    // Unknown error.
    UNKNOWN("unknown error.");

    /**
     * Value
     */
    private final String val;

    /**
     * Constructor
     *
     * @param val value
     */
    ErrorType(String val) {
        this.val = val;
    }

    @Override
    public String value() {
        return this.val;
    }
}
