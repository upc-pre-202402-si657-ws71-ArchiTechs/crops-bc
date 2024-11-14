package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.commands.CreatePestCommand;
import com.microservice.crops.crops.interfaces.REST.resources.CreatePestResource;

public class CreatePestCommandFromResourceAssembler {
    public static CreatePestCommand toCommandFromResource (CreatePestResource resource){
        return new CreatePestCommand(
                resource.name(),
                resource.description(),
                resource.solution()
        );
    }
}
