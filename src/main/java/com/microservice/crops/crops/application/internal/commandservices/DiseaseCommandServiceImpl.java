package com.microservice.crops.crops.application.internal.commandservices;

import com.microservice.crops.crops.domain.model.commands.CreateDiseaseCommand;
import com.microservice.crops.crops.domain.model.entities.Disease;
import com.microservice.crops.crops.domain.services.DiseaseCommandService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseCommandServiceImpl implements DiseaseCommandService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseCommandServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public Long handle(CreateDiseaseCommand command) {
        Disease disease = new Disease(command.name(), command.description(), command.solution());
        disease = diseaseRepository.save(disease);
        return disease.getId();
    }
    @Override
    public void save(Disease disease) {
        diseaseRepository.save(disease);
    }
}