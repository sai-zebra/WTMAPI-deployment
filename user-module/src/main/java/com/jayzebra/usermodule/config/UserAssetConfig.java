package com.jayzebra.usermodule.config;

import com.jayzebra.usermodule.domain.port.in.UserAssetUsecase;
import com.jayzebra.usermodule.domain.port.out.UserAssetsRepositoryPort;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import com.jayzebra.usermodule.domain.service.UserAssetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAssetConfig {

    @Bean
    public UserAssetUsecase userAssetUsecase(UserRepositoryPort userRepositoryPort, UserAssetsRepositoryPort userAssetsRepositoryPort){
        return new UserAssetService(userRepositoryPort,userAssetsRepositoryPort);
    }
}
