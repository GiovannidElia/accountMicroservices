package com.quicktutorial.learnmicroservices.accountMicroservices.common.controller;

import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.NoDataFoundException;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.ClientErrorInformation;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.UserNotLoggedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;


@ControllerAdvice
public class GlobalControllerExceptionHandler {


    @ExceptionHandler({ InvalidParameterException.class })
    public ResponseEntity<ClientErrorInformation> handleInvalidParamException(Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);

    }

    @ExceptionHandler({ NoDataFoundException.class })
    public ResponseEntity<ClientErrorInformation> handleNoDataFoundException(Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    @ExceptionHandler({ UserNotLoggedException.class })
    public ResponseEntity<ClientErrorInformation> handleUserNotLoggedException(Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(HttpStatus.FORBIDDEN.value(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

}
