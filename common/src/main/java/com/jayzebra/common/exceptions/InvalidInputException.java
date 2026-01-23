package com.jayzebra.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // You can optionally add this annotation
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }


    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}

