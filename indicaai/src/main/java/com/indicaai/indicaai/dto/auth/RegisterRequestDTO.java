package com.indicaai.indicaai.dto.auth;

import jakarta.validation.constraints.*;

public record RegisterRequestDTO(

        @NotBlank String nome,
        @Email String email,
        @NotBlank String senha,
        @NotBlank String curso,
        @NotNull Integer semestre
) {}