package com.indicaai.indicaai.service;

import com.indicaai.indicaai.entity.Opportunity;
import com.indicaai.indicaai.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityService {

    private final OpportunityRepository repository;

    public List<Opportunity> listarTodas() {
        return repository.findAll();
    }

    public Opportunity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
    }

    public Opportunity salvar(Opportunity vaga) {
        return repository.save(vaga);
    }

    public Opportunity atualizar(Long id, Opportunity vaga) {
        Opportunity existente = buscarPorId(id);
        existente.setTitulo(vaga.getTitulo());
        existente.setEmpresa(vaga.getEmpresa());
        existente.setDescricao(vaga.getDescricao());
        existente.setRequisitos(vaga.getRequisitos());
        existente.setModalidade(vaga.getModalidade());
        existente.setNivel(vaga.getNivel());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}