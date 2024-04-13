package com.example.lmsservice.exception;

public class UserInfoNotFoundException extends Exception {

    public UserInfoNotFoundException ( String message) {
        super(message);
    }

    public UserInfoNotFoundException ( String message, Throwable cause) {
        super(message, cause);
    }
}
