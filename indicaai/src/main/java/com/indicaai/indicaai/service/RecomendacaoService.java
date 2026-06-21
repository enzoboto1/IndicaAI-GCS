package com.indicaai.indicaai.service;

import com.indicaai.indicaai.dto.RecomendacaoDTO;
import com.indicaai.indicaai.entity.Opportunity;
import com.indicaai.indicaai.entity.User;
import com.indicaai.indicaai.repository.OpportunityRepository;
import com.indicaai.indicaai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecomendacaoService {

    private final UserRepository userRepository;
    private final OpportunityRepository opportunityRepository;

    public List<RecomendacaoDTO> recomendarVagasParaAluno(Long alunoId) {
        User aluno = userRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return opportunityRepository.findAll().stream()
                .map(vaga -> {
                    double compatibilidade = calcularCompatibilidade(
                            aluno.getHabilidades(), vaga.getRequisitos());
                    return new RecomendacaoDTO(
                            aluno.getId(), aluno.getNome(),
                            vaga.getId(), vaga.getTitulo(), vaga.getEmpresa(),
                            compatibilidade);
                })
                .filter(r -> r.getCompatibilidade() > 0)
                .sorted(Comparator.comparingDouble(RecomendacaoDTO::getCompatibilidade).reversed())
                .collect(Collectors.toList());
    }

    public List<RecomendacaoDTO> recomendarAlunosParaVaga(Long vagaId) {
        Opportunity vaga = opportunityRepository.findById(vagaId)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        return userRepository.findAll().stream()
                .map(aluno -> {
                    double compatibilidade = calcularCompatibilidade(
                            aluno.getHabilidades(), vaga.getRequisitos());
                    return new RecomendacaoDTO(
                            aluno.getId(), aluno.getNome(),
                            vaga.getId(), vaga.getTitulo(), vaga.getEmpresa(),
                            compatibilidade);
                })
                .filter(r -> r.getCompatibilidade() > 0)
                .sorted(Comparator.comparingDouble(RecomendacaoDTO::getCompatibilidade).reversed())
                .collect(Collectors.toList());
    }

    private double calcularCompatibilidade(String habilidades, String requisitos) {
        if (habilidades == null || requisitos == null || requisitos.isBlank()) return 0.0;

        String[] habs = habilidades.toLowerCase().split(",");
        String[] reqs = requisitos.toLowerCase().split(",");

        int matches = 0;
        for (String req : reqs) {
            String r = req.trim();
            for (String hab : habs) {
                if (hab.trim().equals(r)) {
                    matches++;
                    break;
                }
            }
        }
        return (matches * 100.0) / reqs.length;
    }
}
