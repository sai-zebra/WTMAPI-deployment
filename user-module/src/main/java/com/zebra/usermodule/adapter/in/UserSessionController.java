package com.zebra.usermodule.adapter.in;

import com.zebra.usermodule.domain.port.in.UserSessionUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// This annotation is needed to get the logged-in user
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/sessions")
@Tag(name = "UserSessions", description = "Endpoints for recording user session events like login and logout.")
@RequiredArgsConstructor
public class UserSessionController {

    private final UserSessionUseCase userSessionUseCase;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void loginSuccess(@AuthenticationPrincipal UserDetails userDetails) {
        // The user's ID is retrieved from the security context, not the request body
        String userId = userDetails.getUsername(); // Or a custom method to get a specific ID
        userSessionUseCase.recordLogin(userId);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        userSessionUseCase.recordLogout(userId);
        return ResponseEntity.noContent().build(); // Standard for 204
    }
}
