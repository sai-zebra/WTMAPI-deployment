package com.jayzebra.usermodule.domain.service;

import com.jayzebra.usermodule.domain.port.in.UserSessionsUsecase;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import com.jayzebra.usermodule.domain.port.out.UserSessionsRepositoryPort;

public class UserSessionsService implements UserSessionsUsecase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserSessionsRepositoryPort sessionsRepositoryPort;
    public UserSessionsService(UserRepositoryPort userRepositoryPort, UserSessionsRepositoryPort sessionsRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.sessionsRepositoryPort = sessionsRepositoryPort;
    }

    @Override
    public String login(String userId) {
        if(userRepositoryPort.existsById(userId)){
            throw new RuntimeException("User does not exit");
        }
    }



    @Override
    public void logout(String sessionId) {
    sessionsRepositoryPort.deleteBySessionId(sessionId);
    }
}
