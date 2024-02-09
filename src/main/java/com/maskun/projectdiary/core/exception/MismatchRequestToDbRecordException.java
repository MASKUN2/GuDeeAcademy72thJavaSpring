package com.maskun.projectdiary.core.exception;

public class MismatchRequestToDbRecordException extends RuntimeException{
    public MismatchRequestToDbRecordException(String message) {
        super(message);
    }

    public MismatchRequestToDbRecordException() {
    }

    public MismatchRequestToDbRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public MismatchRequestToDbRecordException(Throwable cause) {
        super(cause);
    }
}
