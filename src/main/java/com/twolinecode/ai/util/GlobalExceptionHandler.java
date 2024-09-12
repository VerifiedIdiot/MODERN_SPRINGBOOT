package com.twolinecode.ai.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

// 해당 프로젝트는 REST API 송수신에 초점이 맞춰져있습니다.
// REST API 송수신시 발생하는 에러들을 전역적으로 처리합니다.
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {



    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
        log.warn("HTTP Client Error: {}", e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(e.getStatusText());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException e) {
        log.warn("HTTP Server Error: {}", e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(e.getStatusText());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        log.warn("Null pointer exception: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Null pointer exception occurred");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("Illegal argument: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        log.warn("Unexpected error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
    }
}

