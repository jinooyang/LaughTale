package com.jshi.laughtale.common;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authExceptionHandler(AuthenticationException e) {
        log.error("AuthenticationException : {}", e.getMessage());
        e.printStackTrace();
        HttpStatus unauthorized = UNAUTHORIZED;
        return ResponseEntity.status(unauthorized).body(unauthorized.toString());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessExceptionHandler(AccessDeniedException e) {
        log.error("AccessDenied : {}", e.getMessage());
        e.printStackTrace();
        HttpStatus forbidden = FORBIDDEN;
        return ResponseEntity.status(FORBIDDEN).body(forbidden.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException : {}", e.getMessage());
        e.printStackTrace();
        HttpStatus badRequest = BAD_REQUEST;
        return ResponseEntity.status(badRequest).body(badRequest.toString());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> baseExceptionHander(BaseException e) {
        log.error("BaseException : {}", e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<String> servletHandler(ServletException e) {
        log.error("ServletException : {}", e.getMessage());
        e.printStackTrace();
        HttpStatus badRequest = BAD_REQUEST;
        return ResponseEntity.status(badRequest).body(badRequest.toString());
    }

    @ExceptionHandler({UnsupportedJwtException.class, MalformedJwtException.class, ExpiredJwtException.class})
    public ResponseEntity<String> jwtExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(UNAUTHORIZED).body("JWT expired");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        log.error("Exception : {}", e.getMessage());
        e.printStackTrace();
        HttpStatus internalServerError = INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(internalServerError).body(internalServerError.toString());
    }
}
