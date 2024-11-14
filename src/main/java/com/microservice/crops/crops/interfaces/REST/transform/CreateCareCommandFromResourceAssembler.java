package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.commands.CreateCareCommand;
import com.microservice.crops.crops.interfaces.REST.resources.CreateCareResource;

public class CreateCareCommandFromResourceAssembler {

    public static CreateCareCommand toCommandFromResource(CreateCareResource resource) {
        return new CreateCareCommand(
                resource.description(),
                resource.careDate()
        );
    }
}