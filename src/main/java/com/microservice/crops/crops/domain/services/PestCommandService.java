package com.microservice.crops.crops.domain.services;


import com.microservice.crops.crops.domain.model.commands.CreatePestCommand;
import com.microservice.crops.crops.domain.model.entities.Pest;

public interface PestCommandService {
    Long handle(CreatePestCommand command);
    void save(Pest pest);

}
