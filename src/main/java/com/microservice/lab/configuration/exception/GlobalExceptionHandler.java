package com.microservice.lab.configuration.exception;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.telegrambot.RecoveryMessageBot;
import com.microservice.lab.utils.HttpReqRespUtils;
import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.repository.TemporaryTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private HttpReqRespUtils httpReqRespUtils;
    @Autowired
    private TemporaryTokenRepository temporaryTokenRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Value("${telegram.prod}")
    private String prod;
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
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeError(RuntimeException runtimeException) {
        if (prod.equals("STAGING")) {
            RecoveryMessageBot messageBot = new RecoveryMessageBot();
            TokenTemporary token = temporaryTokenRepository.findByUser(authenticationFacade.getAuthentication()).orElse(null);
            messageBot.sendMessage(new Date(), httpReqRespUtils.getIpAddress(), token.getToken(), runtimeException.getMessage());
        }
        return ResponseHelper.err(runtimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
