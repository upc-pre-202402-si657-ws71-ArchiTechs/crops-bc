package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.entities.Care;
import com.microservice.crops.crops.interfaces.REST.resources.CareResource;

public class CareResourceFromEntityAssembler {

    public static CareResource toResourceFromEntity(Care entity) {
        return new CareResource(entity.getId(), entity.getDescription(),entity.getCareDate());
    }
}