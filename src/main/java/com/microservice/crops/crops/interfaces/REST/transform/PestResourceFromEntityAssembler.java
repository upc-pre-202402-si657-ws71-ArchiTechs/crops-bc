package com.microservice.crops.crops.interfaces.REST.transform;


import com.microservice.crops.crops.domain.model.entities.Pest;
import com.microservice.crops.crops.interfaces.REST.resources.PestResource;

public class PestResourceFromEntityAssembler {
    public static PestResource toResourceFromEntity(Pest Entity){
        return new PestResource(
                Entity.getId(),
                Entity.getName(),
                Entity.getDescription(),
                Entity.getSolution()
        );
    }
}
