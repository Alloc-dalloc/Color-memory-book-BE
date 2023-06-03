package com.allocdalloc.colormemorybook.exception.advice;


import com.allocdalloc.colormemorybook.dto.exception.response.ErrorResponseDto;
import com.allocdalloc.colormemorybook.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HttpErrorAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(final IllegalArgumentException e, final HttpServletRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, e.getMessage(), request));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(final ResourceNotFoundException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto.of(HttpStatus.NOT_FOUND, e.getMessage(), request));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(final InvalidTokenException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, e.getMessage(), request));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(final TokenExpiredException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, e.getMessage(), request));
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(final UserUnauthorizedException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.of(HttpStatus.UNAUTHORIZED, e.getMessage(), request));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(final NullPointerException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request));
    }


    @ExceptionHandler(ConflictRequestException.class)
    public ResponseEntity<ErrorResponseDto> handlerException(final ConflictRequestException e, final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponseDto.of(HttpStatus.CONFLICT, e.getMessage(), request));
    }

}
