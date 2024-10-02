package com.optifolio.exceptions;

import com.optifolio.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException ex)
    {
        ErrorResponse response=new ErrorResponse(ex.getErrorTag(),ex.getErrorTag().getTag());
        return new ResponseEntity<>(response,HttpStatus.valueOf(ex.getStatusCode()));
    }
}
