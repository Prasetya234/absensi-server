package com.microservice.lab.configuration.exception;

import com.microservice.lab.configuration.response.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundAdviceController(NotFoundException notFoundException) {
        return ResponseHelper.err(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<?> notInternalAdviceController(BussinesException bussinesException) {
        return ResponseHelper.err(bussinesException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> notBadAdviceController(BadCredentialsException badCredentialsException) {
        return ResponseHelper.err(badCredentialsException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
