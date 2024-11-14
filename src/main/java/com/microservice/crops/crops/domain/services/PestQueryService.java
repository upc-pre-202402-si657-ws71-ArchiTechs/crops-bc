package com.microservice.crops.crops.domain.services;


import com.microservice.crops.crops.domain.model.entities.Pest;
import com.microservice.crops.crops.domain.model.queries.GetAllPestsQuery;
import com.microservice.crops.crops.domain.model.queries.GetPestByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PestQueryService {
    List<Pest> handle(GetAllPestsQuery query);
    Optional<Pest> findById(Long pestId);
    Optional<Pest> handle(GetPestByIdQuery getPestByIdQuery);
}
