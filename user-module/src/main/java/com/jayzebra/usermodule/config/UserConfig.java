package com.jayzebra.usermodule.config;

import com.jayzebra.usermodule.domain.port.in.UserUsecase;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import com.jayzebra.usermodule.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserUsecase userUsecase(UserRepositoryPort userRepositoryPort){
        return new UserService(userRepositoryPort);
    }
}
