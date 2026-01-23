package com.jayzebra.usermodule.config;

import com.jayzebra.usermodule.domain.port.in.UserSessionsUsecase;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import com.jayzebra.usermodule.domain.port.out.UserSessionsRepositoryPort;
import com.jayzebra.usermodule.domain.service.UserSessionsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSessionConfig {
    @Bean
    public UserSessionsUsecase userSessionsUsecase(UserRepositoryPort userRepositoryPort, UserSessionsRepositoryPort userSessionsRepositoryPort){
        return new UserSessionsService(userRepositoryPort, userSessionsRepositoryPort);
    }
}
