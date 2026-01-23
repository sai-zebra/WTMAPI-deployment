package com.jayzebra.usermodule.adapter;

import com.jayzebra.usermodule.adapter.out.repository.UserRepository;
import com.jayzebra.usermodule.domain.dto.UserAssets;
import com.jayzebra.usermodule.domain.port.out.UserAssetsRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAssetsRepository implements UserAssetsRepositoryPort {

    @Override
    public UserAssets save(UserAssets asset) {
        return null;
    }

    @Override
    public Optional<UserAssets> findByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public String existsByUserId(String userId) {
        return "";
    }
}
