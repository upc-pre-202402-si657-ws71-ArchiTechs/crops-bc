package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.commands.CreateDiseaseCommand;
import com.microservice.crops.crops.interfaces.REST.resources.CreateDiseaseResource;

public class CreateDiseaseCommandFromResourceAssembler {

    public static CreateDiseaseCommand toCommandFromResource(CreateDiseaseResource resource) {
        return new CreateDiseaseCommand(
                resource.name(),
                resource.description(),
                resource.solution()
        );
    }
}