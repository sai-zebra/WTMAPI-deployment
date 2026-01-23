package com.jayzebra.usermodule.domain.port.out;

import com.jayzebra.usermodule.domain.service.UserSessionsService;

import java.util.Optional;

public interface UserSessionsRepositoryPort {
    UserSessionsService save (UserSessionsService session);

    Optional<UserSessionsService> findBySessionId(String sessionId);

    Optional<UserSessionsService> findActiveSessionsByUserId(String userId);

    void deleteBySessionId(String sessionId);
}
