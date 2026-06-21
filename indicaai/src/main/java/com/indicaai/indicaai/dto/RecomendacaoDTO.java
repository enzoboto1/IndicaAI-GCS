package com.indicaai.indicaai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecomendacaoDTO {
    private Long alunoId;
    private String alunoNome;
    private Long vagaId;
    private String vagaTitulo;
    private String empresa;
    private Double compatibilidade;
}
