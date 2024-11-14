package com.microservice.crops.crops.domain.services;


import com.microservice.crops.crops.domain.model.entities.Disease;
import com.microservice.crops.crops.domain.model.queries.GetAllDiseasesQuery;

import java.util.List;
import java.util.Optional;

public interface DiseaseQueryService {
    List<Disease> handle(GetAllDiseasesQuery query);
    Optional<Disease> findById(Long diseaseId);
}
