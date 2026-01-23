package com.jayzebra.usermodule.adapter.in;

import com.jayzebra.usermodule.domain.port.in.UserSessionsUsecase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
public class UserSessionController {
    private final UserSessionsUsecase userSessionsUsecase;

    public UserSessionController(UserSessionsUsecase userSessionsUsecase) {
        this.userSessionsUsecase = userSessionsUsecase;
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId){
        return userSessionsUsecase.login(userId);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String sessionId){

    }
}
