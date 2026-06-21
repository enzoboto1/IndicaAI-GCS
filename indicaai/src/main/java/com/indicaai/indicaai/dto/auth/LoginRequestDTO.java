package com.indicaai.indicaai.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequestDTO {
    @Email private String email;
    @NotBlank private String senha;
    public LoginRequestDTO() {}
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}
