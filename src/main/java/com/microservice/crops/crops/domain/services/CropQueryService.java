package com.microservice.crops.crops.domain.services;


import com.microservice.crops.crops.domain.model.aggregates.Crop;
import com.microservice.crops.crops.domain.model.queries.GetAllCropsQuery;
import com.microservice.crops.crops.domain.model.queries.GetCropByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CropQueryService {
    List<Crop> handle(GetAllCropsQuery query);
    Optional<Crop> handle(GetCropByIdQuery query);
    Optional<Crop> findById(Long id);
}