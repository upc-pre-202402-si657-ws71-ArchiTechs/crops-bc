package com.microservice.crops.crops.infrastructure.persistence.jpa.repositories;

import com.microservice.crops.crops.domain.model.entities.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    Optional<Disease> findById(Long id);
}
