package com.jayzebra.usermodule.domain.service;

import com.jayzebra.usermodule.domain.port.in.UserAssetUsecase;
import com.jayzebra.usermodule.domain.port.out.UserAssetsRepositoryPort;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;

public class UserAssetService implements UserAssetUsecase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserAssetsRepositoryPort userAssetRepositoryPort;

    public UserAssetService(UserRepositoryPort userRepositoryPort, UserAssetsRepositoryPort userAssetRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.userAssetRepositoryPort = userAssetRepositoryPort;
    }

    @Override
    public void uploadUserImage(String userId, byte[] image) {
        if(userAssetRepositoryPort.existsByUserId(userId)){
            throw new RuntimeException("User not found");
        }
    }

}
