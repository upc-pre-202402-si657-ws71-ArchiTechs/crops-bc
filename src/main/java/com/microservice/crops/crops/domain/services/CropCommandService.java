package com.microservice.crops.crops.domain.services;


import com.microservice.crops.crops.domain.model.aggregates.Crop;
import com.microservice.crops.crops.domain.model.commands.CreateCropCommand;
import com.microservice.crops.crops.domain.model.commands.DeleteCropCommand;
import com.microservice.crops.crops.domain.model.commands.UpdateCropCommand;

import java.util.Optional;

public interface CropCommandService {
    Long handle(CreateCropCommand command);
    Optional<Crop> handle(UpdateCropCommand command);
    void handle(DeleteCropCommand command);

    void save(Crop crop);
}