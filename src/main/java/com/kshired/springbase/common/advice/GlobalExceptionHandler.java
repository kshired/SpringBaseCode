package com.kshired.springbase.common.advice;


import com.kshired.springbase.common.exception.BadRequestException;
import com.kshired.springbase.common.exception.NotFoundException;
import com.kshired.springbase.common.exception.UnauthorizedException;
import com.kshired.springbase.common.utils.ApiUtils;
import com.kshired.springbase.common.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ApiResult<?>> errorResponse(Throwable throwable, HttpStatus status) {
        return errorResponse(throwable.getMessage(), status);
    }

    private ResponseEntity<ApiResult<?>> errorResponse(String message, HttpStatus status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return new ResponseEntity<>(ApiUtils.error(message), httpHeaders, status);
    }

    @ExceptionHandler({
            BadRequestException.class,
            IllegalArgumentException.class,
            IllegalStateException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<?> handleClientException(Exception e) {
        log.error("Bad request exception occurred: {}", e.getMessage(), e);
        if (e instanceof MethodArgumentNotValidException) {
            return errorResponse(
                    ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
        return errorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            NoHandlerFoundException.class,
            NotFoundException.class
    })
    public ResponseEntity<?> handleNotFoundException(Exception e){
        log.error("Not found exception occurred: {}", e.getMessage(), e);
        return errorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            UnauthorizedException.class
    })
    public ResponseEntity<?> handleUnauthorizedException(Exception e){
        log.error("Unauthorized exception occurred: {}", e.getMessage(), e);
        return errorResponse(e, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
