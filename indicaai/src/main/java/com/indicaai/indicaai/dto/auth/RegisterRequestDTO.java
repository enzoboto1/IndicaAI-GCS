package com.indicaai.indicaai.dto.auth;

import javax.validation.constraints.*;

public class RegisterRequestDTO {
    @NotBlank private String nome;
    @Email private String email;
    @NotBlank private String senha;
    @NotBlank private String curso;
    @NotNull private Integer semestre;
    public RegisterRequestDTO() {}
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getCurso() { return curso; }
    public Integer getSemestre() { return semestre; }
}
