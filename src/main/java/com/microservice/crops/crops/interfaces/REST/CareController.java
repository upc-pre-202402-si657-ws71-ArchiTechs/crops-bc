package com.microservice.crops.crops.interfaces.REST;

import com.microservice.crops.crops.domain.model.entities.Care;
import com.microservice.crops.crops.domain.model.queries.GetAllCaresQuery;
import com.microservice.crops.crops.domain.services.CareCommandService;
import com.microservice.crops.crops.domain.services.CareQueryService;
import com.microservice.crops.crops.domain.services.CropCommandService;
import com.microservice.crops.crops.domain.services.CropQueryService;
import com.microservice.crops.crops.interfaces.REST.resources.CareResource;
import com.microservice.crops.crops.interfaces.REST.resources.CreateCareResource;
import com.microservice.crops.crops.interfaces.REST.transform.CareResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/crops/cares")
//@Tag(name = "Cares", description = "Cares Management Endpoints")
public class CareController {

    private final CareCommandService careCommandService;
    private final CareQueryService careQueryService;
    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    public CareController(CareCommandService careCommandService,
                          CareQueryService careQueryService,
                          CropCommandService cropCommandService,
                            CropQueryService cropQueryService) {
        this.careCommandService = careCommandService;
        this.careQueryService = careQueryService;
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }


    @PostMapping
    public ResponseEntity<CareResource> createCare(@RequestBody CreateCareResource careResource) {
        Care care = new Care(careResource.description(), careResource.careDate());
        careCommandService.save(care);

        CareResource careResourceResponse = CareResourceFromEntityAssembler.toResourceFromEntity(care);
        return ResponseEntity.ok(careResourceResponse);
    }

    @GetMapping
    public ResponseEntity<List<CareResource>> getAllCares() {
        var getAllCaresQuery = new GetAllCaresQuery();
        var cares = careQueryService.handle(getAllCaresQuery);
        var careResource = cares.stream().map(CareResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(careResource);
    }


}