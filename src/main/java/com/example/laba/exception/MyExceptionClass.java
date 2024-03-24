package com.example.laba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class MyException {

    @ExceptionHandler({InternalServerErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ApiError>  internalServerError(Exception e) {
        String errorUUID = logErrorToNoSql(e);
        ApiError apiError = new ApiError();
        apiError.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setErrorMessage("Внутренняя ошибка сервера: " + e.getMessage()+errorUUID);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    private String logErrorToNoSql(Exception e) {
        return UUID.randomUUID().toString();
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiError> badRequest(Exception e){
        String errorUUID = logErrorToNoSql(e);
        ApiError apiError = new ApiError();

    }
}

