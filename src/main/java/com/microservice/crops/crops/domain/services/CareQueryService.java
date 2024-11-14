package com.microservice.crops.crops.domain.services;


import com.microservice.crops.crops.domain.model.entities.Care;
import com.microservice.crops.crops.domain.model.queries.GetAllCaresQuery;
import com.microservice.crops.crops.domain.model.queries.GetCaresByCropIdQuery;

import java.util.List;
import java.util.Optional;

public interface CareQueryService {
    List<Care> handle(GetAllCaresQuery query);

    Optional<Care> handle(GetCaresByCropIdQuery query);

    Optional<Care> findById(Long careId);


}
