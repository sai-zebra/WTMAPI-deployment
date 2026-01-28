// common-exceptions/src/main/java/com/yourcompany/myworkapi/common/exceptions/ErrorResponse.java

package com.jayzebra.common;

import com.fasterxml.jackson.annotation.JsonInclude; // Import this
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // This is good practice: hides null fields in the JSON output
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private List<String> details; // Field for validation error details

    // --- Constructor for standard errors (without details) ---
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // ===================================================================
    //  EDIT: I've added a second constructor for validation errors.
    //
    //  Your GlobalExceptionHandler can now use this constructor directly, like:
    //  `new ErrorResponse(..., ..., ..., ..., ..., validationDetails);`
    //  This is a more robust pattern than creating the object and then calling a setter.
    // ===================================================================
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path, List<String> details) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }
}
