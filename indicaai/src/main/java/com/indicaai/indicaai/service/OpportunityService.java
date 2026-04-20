package com.indicaai.indicaai.service;

import com.indicaai.indicaai.dto.opportunity.OpportunityRequestDTO;
import com.indicaai.indicaai.dto.opportunity.OpportunityResponseDTO;
import com.indicaai.indicaai.entity.Opportunity;
import com.indicaai.indicaai.entity.OpportunityType;
import com.indicaai.indicaai.entity.User;
import com.indicaai.indicaai.repository.OpportunityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityService {

    private final OpportunityRepository repository;

    public OpportunityResponseDTO create(OpportunityRequestDTO dto, User user) {
        Opportunity opportunity = Opportunity.builder()
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .tipo(dto.tipo())
                .empresa(dto.empresa())
                .area(dto.area())
                .localizacao(dto.localizacao())
                .remuneracao(dto.remuneracao())
                .contato(dto.contato())
                .usuario(user)
                .build();

        return mapToResponse(repository.save(opportunity));
    }

    public List<OpportunityResponseDTO> listAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<OpportunityResponseDTO> search(
            String title,
            String area,
            OpportunityType tipo) {

        return repository.search(title, area, tipo)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OpportunityResponseDTO mapToResponse(Opportunity o) {
        return new OpportunityResponseDTO(
                o.getId(),
                o.getTitulo(),
                o.getDescricao(),
                o.getTipo(),
                o.getEmpresa(),
                o.getArea(),
                o.getLocalizacao(),
                o.getRemuneracao(),
                o.getContato(),
                o.getUsuario().getNome()
        );
    }
}