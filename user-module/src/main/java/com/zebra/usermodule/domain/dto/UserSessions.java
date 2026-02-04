package com.zebra.usermodule.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSessions {
    private String userId;
    private String sessionId;
}
