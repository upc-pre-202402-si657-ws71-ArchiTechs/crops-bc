package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.commands.UpdateCropCommand;
import com.microservice.crops.crops.interfaces.REST.resources.UpdateCropResource;

public class UpdateCropCommandFromResourceAssembler {
    public static UpdateCropCommand toCommandFromResource(Long cropId, UpdateCropResource resource) {
        return new UpdateCropCommand(cropId, resource.name(), resource.description());
    }
}
