package com.jayzebra.usermodule.adapter.in;

import com.jayzebra.usermodule.domain.port.in.UserUsecase;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserUsecase userUsecase;

    public UserController(UserUsecase userUsecase) {
        this.userUsecase = userUsecase;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userUsecase.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId){
        return (User) userUsecase.getUserById(userId);
    }
}
