package com.jayzebra.usermodule.adapter.out.repository;

import com.jayzebra.usermodule.adapter.out.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity, String> {
}
