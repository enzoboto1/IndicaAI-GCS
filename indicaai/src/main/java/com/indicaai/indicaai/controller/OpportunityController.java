package com.indicaai.indicaai.controller;

import com.indicaai.indicaai.dto.opportunity.OpportunityRequestDTO;
import com.indicaai.indicaai.dto.opportunity.OpportunityResponseDTO;
import com.indicaai.indicaai.entity.OpportunityType;
import com.indicaai.indicaai.entity.User;
import com.indicaai.indicaai.service.OpportunityService;
import com.indicaai.indicaai.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/opportunities")
@RequiredArgsConstructor
public class OpportunityController {

    private final OpportunityService service;
    private final UserService userService;

    @PostMapping
    public OpportunityResponseDTO create(
            @RequestBody @Valid OpportunityRequestDTO dto,
            Authentication authentication) {

        String email = authentication.getName();
        User user = userService.getAuthenticatedUser(email);
        return service.create(dto, user);
    }

    @GetMapping
    public List<OpportunityResponseDTO> list() {
        return service.listAll();
    }

    @GetMapping("/search")
    public List<OpportunityResponseDTO> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) OpportunityType tipo) {

        return service.search(title, area, tipo);
    }
}
