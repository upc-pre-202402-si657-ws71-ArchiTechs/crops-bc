package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.entities.Disease;
import com.microservice.crops.crops.interfaces.REST.resources.DiseaseResource;

public class DiseaseResourceFromEntityAssembler {

    public static DiseaseResource toResourceFromEntity(Disease entity) {
        return new DiseaseResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getSolution()
        );
    }
}