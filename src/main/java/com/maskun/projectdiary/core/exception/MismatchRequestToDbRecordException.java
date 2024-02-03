package com.maskun.projectdiary.core.exception;

public class MismatchRequestToDbRecordException extends RuntimeException{
    public MismatchRequestToDbRecordException(String message) {
        super(message);
    }

    public MismatchRequestToDbRecordException() {
    }
}
