package com.zebra.usermodule.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImageUrl;

}

