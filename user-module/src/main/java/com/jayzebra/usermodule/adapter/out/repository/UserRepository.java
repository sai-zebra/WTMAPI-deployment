package com.jayzebra.usermodule.adapter.out.repository;

import com.jayzebra.usermodule.adapter.out.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
