package com.microservice.crops.crops.domain.services;

import com.microservice.crops.crops.domain.model.commands.CreateDiseaseCommand;
import com.microservice.crops.crops.domain.model.entities.Disease;
import org.springframework.stereotype.Service;

@Service
public interface DiseaseCommandService {
    Long handle(CreateDiseaseCommand command);
    void save(Disease disease);

}
