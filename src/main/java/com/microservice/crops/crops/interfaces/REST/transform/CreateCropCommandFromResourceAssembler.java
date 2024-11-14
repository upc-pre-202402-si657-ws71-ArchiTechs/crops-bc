package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.commands.CreateCropCommand;
import com.microservice.crops.crops.interfaces.REST.resources.CreateCropResource;

public class CreateCropCommandFromResourceAssembler {

    public static CreateCropCommand toCommandFromResource(CreateCropResource resource) {
        return new CreateCropCommand(
                resource.name(),
                resource.description(),
                resource.imageUrl(),
                resource.diseases(),
                resource.pests(),
                resource.cares()
        );
    }
}