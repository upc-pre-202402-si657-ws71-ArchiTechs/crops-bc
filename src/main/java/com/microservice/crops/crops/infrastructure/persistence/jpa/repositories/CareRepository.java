package com.microservice.crops.crops.infrastructure.persistence.jpa.repositories;

import com.microservice.crops.crops.domain.model.entities.Care;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareRepository extends JpaRepository<Care, Long> {
    Optional<Care> findById(Long id);

}