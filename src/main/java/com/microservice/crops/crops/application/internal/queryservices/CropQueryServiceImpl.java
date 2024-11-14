package com.microservice.crops.crops.application.internal.queryservices;

import com.microservice.crops.crops.domain.model.aggregates.Crop;
import com.microservice.crops.crops.domain.model.queries.GetAllCropsQuery;
import com.microservice.crops.crops.domain.model.queries.GetCropByIdQuery;
import com.microservice.crops.crops.domain.services.CropQueryService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropQueryServiceImpl implements CropQueryService {

    private final CropRepository cropRepository;

    public CropQueryServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    public List<Crop> handle(GetAllCropsQuery query) {
        return cropRepository.findAll();
    }

    @Override
    public Optional<Crop> handle(GetCropByIdQuery query) {
        return cropRepository.findById(query.id());
    }

    @Override
    public Optional<Crop> findById(Long id) {
        return cropRepository.findById(id);
    }
}