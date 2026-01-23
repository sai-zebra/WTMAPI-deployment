package com.jayzebra.usermodule.adapter.out.repository;

import com.jayzebra.usermodule.adapter.out.entity.UserAssetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAssetRepository extends JpaRepository<UserAssetsEntity, String> {
}
