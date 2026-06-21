package com.indicaai.indicaai.controller;

import com.indicaai.indicaai.entity.Opportunity;
import com.indicaai.indicaai.service.OpportunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vagas")
@RequiredArgsConstructor
public class OpportunityController {

    private final OpportunityService service;

    @GetMapping
    public List<Opportunity> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Opportunity buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Opportunity criar(@RequestBody Opportunity vaga) {
        return service.salvar(vaga);
    }

    @PutMapping("/{id}")
    public Opportunity atualizar(@PathVariable Long id, @RequestBody Opportunity vaga) {
        return service.atualizar(id, vaga);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
