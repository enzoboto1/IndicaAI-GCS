package com.indicaai.indicaai.controller;

import com.indicaai.indicaai.entity.User;
import com.indicaai.indicaai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public User me(Authentication authentication) {
        return userService.getAuthenticatedUser(authentication.getName());
    }
}