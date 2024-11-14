package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.aggregates.Crop;
import com.microservice.crops.crops.interfaces.REST.resources.CropResource;

public class CropResourceFromEntityAssembler {
    public static CropResource toResourceFromEntity(Crop entity) {
        return new CropResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getImageUrl(), entity.getDiseaseIds(), entity.getPestIds(),entity.getCareIds());
    }
}
