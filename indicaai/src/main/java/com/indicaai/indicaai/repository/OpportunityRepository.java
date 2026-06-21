package com.indicaai.indicaai.repository;

import com.indicaai.indicaai.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
}
