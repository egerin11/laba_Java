package com.example.laba.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionClass {
  private static final Logger logger = LoggerFactory.getLogger(MyExceptionClass.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception e) {
    String errorMessage = "Внутренняя ошибка сервера: " + e.getMessage();
    logger.error(errorMessage, e);

    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<ApiError> handleEntityNotFound(ResourceNotFoundException ex) {
    String errorMessage = "Ошибка запроса: " + ex.getMessage();
    logger.error(errorMessage, ex);

    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), errorMessage);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
  }
}
