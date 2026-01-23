package com.jayzebra.usermodule.adapter.out.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserSessionEntity {
    private String userId;
    private String sessionId;
}
