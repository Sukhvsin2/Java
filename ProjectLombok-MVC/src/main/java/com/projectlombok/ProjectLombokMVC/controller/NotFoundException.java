package com.projectlombok.ProjectLombokMVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// adding this to avoid extra code written in the exception controller
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Value not found")
public class NotFoundException extends RuntimeException{
    public NotFoundException(){}

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
