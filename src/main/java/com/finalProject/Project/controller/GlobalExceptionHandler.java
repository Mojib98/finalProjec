package com.finalProject.Project.controller;

import com.finalProject.Project.exception.InvalinInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(InvalinInput.class)
    public ResponseEntity<Object> inputError(Exception ex, WebRequest webRequest){
        logger.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Some Thing Wrong " + ex.getMessage());

    }
    public ResponseEntity<Object> exception(RuntimeException runtimeException){
        logger.error(runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Some Thing Wrong " + runtimeException.getMessage());
    }
}
