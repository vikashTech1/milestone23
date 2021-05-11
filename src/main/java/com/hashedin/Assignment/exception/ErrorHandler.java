package com.hashedin.Assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = ListEmptyException.class)
    public ResponseEntity<Object> exception(ListEmptyException exception) {
        return new ResponseEntity<>("List is empty", HttpStatus.BAD_REQUEST);
    }
}
