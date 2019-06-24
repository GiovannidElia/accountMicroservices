package com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions;

public class UserNotLoggedException extends Exception {

    public UserNotLoggedException(String errorMessage){
        super(errorMessage);
    }

    public UserNotLoggedException(String message, Throwable cause) {
        super(message, cause);
    }
}
