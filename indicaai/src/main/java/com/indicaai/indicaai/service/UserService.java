package com.indicaai.indicaai.service;

import com.indicaai.indicaai.entity.User;
import com.indicaai.indicaai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> listarTodos() {
        return repository.findAll();
    }

    public User buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public User salvar(User aluno) {
        return repository.save(aluno);
    }

    public User atualizar(Long id, User aluno) {
        User existente = buscarPorId(id);
        existente.setNome(aluno.getNome());
        existente.setEmail(aluno.getEmail());
        existente.setCurso(aluno.getCurso());
        existente.setSemestre(aluno.getSemestre());
        existente.setHabilidades(aluno.getHabilidades());
        existente.setGithub(aluno.getGithub());
        existente.setLinkedin(aluno.getLinkedin());
        existente.setPortfolio(aluno.getPortfolio());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public User getAuthenticatedUser(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}