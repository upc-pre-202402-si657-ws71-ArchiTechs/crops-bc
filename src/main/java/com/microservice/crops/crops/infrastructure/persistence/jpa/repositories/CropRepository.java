package com.microservice.crops.crops.infrastructure.persistence.jpa.repositories;

import com.microservice.crops.crops.domain.model.aggregates.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Long>{
    Optional<Crop> findById(Long id);
}