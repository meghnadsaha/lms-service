package com.example.lmsservice.exception;

public class TokenExpiredException extends Exception {

    public TokenExpiredException ( String message) {
        super(message);
    }

    public TokenExpiredException ( String message, Throwable cause) {
        super(message, cause);
    }
}
