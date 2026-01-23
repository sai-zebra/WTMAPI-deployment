package com.jayzebra.usermodule.domain.port.in;

public interface UserAssetUsecase {
    void uploadUserImage(String userId, byte[]image);
}
