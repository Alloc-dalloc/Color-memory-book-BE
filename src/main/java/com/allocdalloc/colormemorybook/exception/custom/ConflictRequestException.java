package com.allocdalloc.colormemorybook.exception.custom;

public class ConflictRequestException extends RuntimeException {
    public ConflictRequestException(String message) {
        super(message);
    }
}
