package com.indicaai.indicaai.controller;

import com.indicaai.indicaai.entity.User;
import com.indicaai.indicaai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> listar() {
        return userService.listarTodos();
    }

    @GetMapping("/{id}")
    public User buscar(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User criar(@RequestBody User aluno) {
        return userService.salvar(aluno);
    }

    @PutMapping("/{id}")
    public User atualizar(@PathVariable Long id, @RequestBody User aluno) {
        return userService.atualizar(id, aluno);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        userService.deletar(id);
    }
}