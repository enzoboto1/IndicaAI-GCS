package com.indicaai.indicaai.dto.opportunity;

import com.indicaai.indicaai.entity.OpportunityType;

import java.math.BigDecimal;

public record OpportunityResponseDTO(
        Long id,
        String titulo,
        String descricao,
        OpportunityType tipo,
        String empresa,
        String area,
        String localizacao,
        BigDecimal remuneracao,
        String contato,
        String autor
) {}
