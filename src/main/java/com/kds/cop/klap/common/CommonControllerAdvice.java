package com.kds.cop.klap.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 * CommonControllerAdvice
 * RestController 공통 예외처리
 *
 */
@Slf4j
@RestControllerAdvice("com.kds.cop.klap")
public class CommonControllerAdvice {
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<String> example(RuntimeException exception,
                                             Object body,
                                             WebRequest request) throws JsonProcessingException {
        log.debug("RestCtrlAdvice");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"example\"}");
    }
}

