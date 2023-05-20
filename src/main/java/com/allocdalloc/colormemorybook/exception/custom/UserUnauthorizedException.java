package com.allocdalloc.colormemorybook.exception.custom;

public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException(String message) {
        super(message);
    }
}
