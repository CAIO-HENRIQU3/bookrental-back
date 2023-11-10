package com.caiohenrique.bookrental.exceptions;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Getter
@RestControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ExceptionHandlerConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessExceptions.class)
    public ResponseEntity<Object> handleCustomException(BusinessExceptions error, WebRequest request) {
        ErrorException errorResponse = new ErrorException(HttpStatus.BAD_REQUEST.value(), error.getMessage());
        return handleExceptionInternal(error, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(NotFoundExceptions.class)
    public ResponseEntity<Object> notFoundCustomException(NotFoundExceptions error, WebRequest request) {
        ErrorException errorResponse = new ErrorException(HttpStatus.NOT_FOUND.value(), error.getMessage());
        return handleExceptionInternal(error, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}