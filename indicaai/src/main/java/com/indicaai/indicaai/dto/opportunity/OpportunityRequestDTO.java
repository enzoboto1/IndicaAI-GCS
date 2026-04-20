package com.indicaai.indicaai.dto.opportunity;

import com.indicaai.indicaai.entity.OpportunityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OpportunityRequestDTO(

        @NotBlank String titulo,
        @NotBlank String descricao,
        @NotNull OpportunityType tipo,
        @NotBlank String empresa,
        @NotBlank String area,
        @NotBlank String localizacao,
        BigDecimal remuneracao,
        @NotBlank String contato
) {}