package com.microservice.crops.crops.infrastructure.persistence.jpa.repositories;

import com.microservice.crops.crops.domain.model.entities.Pest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PestRepository extends JpaRepository <Pest, Integer> {
    Optional<Pest> findById(Long id);
}
