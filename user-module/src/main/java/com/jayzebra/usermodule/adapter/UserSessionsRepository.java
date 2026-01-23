package com.jayzebra.usermodule.adapter;

import com.jayzebra.usermodule.domain.port.out.UserSessionsRepositoryPort;
import com.jayzebra.usermodule.domain.service.UserSessionsService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserSessionsRepository implements UserSessionsRepositoryPort {
    @Override
    public UserSessionsService save(UserSessionsService session) {
        return null;
    }

    @Override
    public Optional<UserSessionsService> findBySessionId(String sessionId) {
        return Optional.empty();
    }

    @Override
    public Optional<UserSessionsService> findActiveSessionsByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public void deleteBySessionId(String sessionId) {

    }
}
