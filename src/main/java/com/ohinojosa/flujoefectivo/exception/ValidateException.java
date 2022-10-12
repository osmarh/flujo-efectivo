package com.ohinojosa.flujoefectivo.exception;

public class ValidateException extends RuntimeException{
    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
