package com.jayzebra.usermodule.domain.port.out;

import com.jayzebra.usermodule.domain.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    List<User> findAll();
    Optional<User> findById(String userId);
    String existsById(String userId);

    User save(User user);
}
