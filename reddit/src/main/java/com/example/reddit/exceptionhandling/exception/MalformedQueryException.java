package com.example.reddit.exceptionhandling.exception;

public class MalformedQueryException extends RuntimeException {

    public MalformedQueryException(String message) {
        super(message);
    }
}
