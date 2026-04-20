package com.indicaai.indicaai.controller;

import com.indicaai.indicaai.dto.auth.AuthResponseDTO;
import com.indicaai.indicaai.dto.auth.LoginRequestDTO;
import com.indicaai.indicaai.dto.auth.RegisterRequestDTO;
import com.indicaai.indicaai.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequestDTO dto) {
        authService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) {
        return authService.login(dto);
    }
}