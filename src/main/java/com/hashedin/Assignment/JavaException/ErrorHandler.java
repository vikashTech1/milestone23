package com.hashedin.Assignment.JavaException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = ExceptionClass.class)
    public ResponseEntity<Object> exception(ExceptionClass exception){
        return new ResponseEntity<>("No Data Available", HttpStatus.BAD_REQUEST);
    }
}
