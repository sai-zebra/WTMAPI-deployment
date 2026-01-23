// common-exceptions/src/main/java/com/yourcompany/myworkapi/common/exceptions/ErrorResponse.java

package com.jayzebra.common;

import com.fasterxml.jackson.annotation.JsonInclude; // Import this
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<String> details; // This is for validation error details

    // --- Constructors ---

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }


}
