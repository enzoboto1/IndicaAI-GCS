package com.indicaai.indicaai.controller;

import com.indicaai.indicaai.dto.RecomendacaoDTO;
import com.indicaai.indicaai.service.RecomendacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recomendacoes")
@RequiredArgsConstructor
public class RecomendacaoController {

    private final RecomendacaoService service;

    @GetMapping("/aluno/{id}")
    public List<RecomendacaoDTO> vagasParaAluno(@PathVariable Long id) {
        return service.recomendarVagasParaAluno(id);
    }

    @GetMapping("/vaga/{id}")
    public List<RecomendacaoDTO> alunosParaVaga(@PathVariable Long id) {
        return service.recomendarAlunosParaVaga(id);
    }
}
