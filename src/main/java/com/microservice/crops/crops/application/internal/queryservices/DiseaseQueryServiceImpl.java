package com.microservice.crops.crops.application.internal.queryservices;

import com.microservice.crops.crops.domain.model.entities.Disease;
import com.microservice.crops.crops.domain.model.queries.GetAllDiseasesQuery;
import com.microservice.crops.crops.domain.services.DiseaseQueryService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseQueryServiceImpl implements DiseaseQueryService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseQueryServiceImpl(DiseaseRepository diseaseRepository, CropRepository cropRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public List<Disease> handle(GetAllDiseasesQuery query) {
        return diseaseRepository.findAll();
    }

    @Override
    public Optional<Disease> findById(Long diseaseId) {
        return diseaseRepository.findById(Math.toIntExact(diseaseId));
    }

}