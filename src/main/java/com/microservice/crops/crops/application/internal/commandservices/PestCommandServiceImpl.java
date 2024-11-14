package com.microservice.crops.crops.application.internal.commandservices;

import com.microservice.crops.crops.domain.model.commands.CreatePestCommand;
import com.microservice.crops.crops.domain.model.entities.Pest;
import com.microservice.crops.crops.domain.services.PestCommandService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PestCommandServiceImpl implements PestCommandService {

    private final PestRepository pestRepository;

    @Autowired
    public PestCommandServiceImpl(PestRepository pestRepository) {
        this.pestRepository = pestRepository;
    }

    @Override
    public Long handle(CreatePestCommand command) {
        Pest pest = new Pest(command.name(), command.description(), command.solution());
        pest = pestRepository.save(pest);
        return pest.getId();
    }

    @Override
    public void save(Pest pest) {
        pestRepository.save(pest);
    }

}
