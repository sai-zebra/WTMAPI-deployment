package com.jayzebra.usermodule.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class User {
    private String userId;
    @NotBlank(message = "UserName Should be of aplhanumeric")
    private String userName;
    private String firstName;
    private String lastName;
    @Email
    private String email;
}
