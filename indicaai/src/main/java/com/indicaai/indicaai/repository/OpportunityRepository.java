package com.indicaai.indicaai.repository;

import com.indicaai.indicaai.entity.Opportunity;
import com.indicaai.indicaai.entity.OpportunityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    List<Opportunity> findByTituloContainingIgnoreCase(String titulo);

    List<Opportunity> findByTipo(OpportunityType tipo);

    List<Opportunity> findByAreaContainingIgnoreCase(String area);

    @Query("""
                SELECT o FROM Opportunity o
                WHERE (:title IS NULL OR LOWER(o.titulo) LIKE LOWER(CONCAT('%', :title, '%')))
                  AND (:area IS NULL OR LOWER(o.area) LIKE LOWER(CONCAT('%', :area, '%')))
                  AND (:tipo IS NULL OR o.tipo = :tipo)
            """)
    List<Opportunity> search(
            String title,
            String area,
            OpportunityType tipo);
}
