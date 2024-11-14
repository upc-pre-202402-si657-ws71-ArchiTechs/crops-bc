package com.microservice.crops.crops.application.internal.commandservices;


import com.microservice.crops.crops.domain.model.commands.CreateCareCommand;
import com.microservice.crops.crops.domain.model.entities.Care;
import com.microservice.crops.crops.domain.services.CareCommandService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.CareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareCommandServiceImpl implements CareCommandService {

    private final CareRepository careRepository;

    @Autowired
    public CareCommandServiceImpl(CareRepository careRepository) {
        this.careRepository = careRepository;
    }


    @Override
    public Long handle(CreateCareCommand command) {
        Care care = new Care(command.description(), command.careDate());
        care = careRepository.save(care);
        return care.getId();
    }
    @Override
    public void save(Care care) {
        careRepository.save(care);
    }

}