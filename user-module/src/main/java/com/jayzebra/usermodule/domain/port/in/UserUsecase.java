package com.jayzebra.usermodule.domain.port.in;

import com.jayzebra.usermodule.domain.dto.User;
import org.hibernate.annotations.processing.Find;

import java.util.List;

public interface UserUsecase {
    List<User> listAllUsers();
    User getUserById(String userId);

    List<org.apache.catalina.User> getAllUsers();
}
