package com.jayzebra.usermodule.domain.port.in;

public interface UserSessionsUsecase {
    String login(String userId);
    void logout(String sessionId);
}
