package com.jayzebra.usermodule.domain.service;

import com.jayzebra.usermodule.adapter.out.repository.UserRepository;
import com.jayzebra.usermodule.domain.dto.User;
import com.jayzebra.usermodule.domain.port.in.UserSessionsUsecase;
import com.jayzebra.usermodule.domain.port.in.UserUsecase;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;

import java.util.List;

public class UserService implements UserUsecase, UserSessionsUsecase {

    private final UserRepositoryPort repository;

    public UserService(UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<User> listAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return repository.findById(userId).orElseThrow(() ->new RuntimeException("User not Found"));
    }

    @Override
    public List<org.apache.catalina.User> getAllUsers() {
        return List.of();
    }

    @Override
    public String login(String userId) {
        return "";
    }

    @Override
    public void logout(String sessionId) {

    }
}
