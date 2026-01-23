package com.jayzebra.usermodule.domain.port.out;

import com.jayzebra.usermodule.domain.dto.UserAssets;

import java.util.Optional;

public interface UserAssetsRepositoryPort {
    UserAssets save(UserAssets asset);

    Optional<UserAssets> findByUserId(String userId);

    String existsByUserId(String userId);
}
