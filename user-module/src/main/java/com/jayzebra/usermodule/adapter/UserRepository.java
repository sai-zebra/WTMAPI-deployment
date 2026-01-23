package com.jayzebra.usermodule.adapter;

import com.jayzebra.usermodule.domain.dto.User;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements UserRepositoryPort {
    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.empty();
    }

    @Override
    public String existsById(String userId) {
        return "";
    }

    @Override
    public User save(User user) {
        return null;
    }
}
