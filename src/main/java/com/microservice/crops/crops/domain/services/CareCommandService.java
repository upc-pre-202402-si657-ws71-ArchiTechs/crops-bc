package com.microservice.crops.crops.domain.services;


import com.microservice.crops.crops.domain.model.commands.CreateCareCommand;
import com.microservice.crops.crops.domain.model.entities.Care;

public interface CareCommandService {
    Long handle(CreateCareCommand command);

    void save(Care care);

}