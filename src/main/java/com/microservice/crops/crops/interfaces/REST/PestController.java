package com.microservice.crops.crops.interfaces.REST;

import com.microservice.crops.crops.domain.model.entities.Pest;
import com.microservice.crops.crops.domain.model.queries.GetAllPestsQuery;
import com.microservice.crops.crops.domain.services.CropCommandService;
import com.microservice.crops.crops.domain.services.CropQueryService;
import com.microservice.crops.crops.domain.services.PestCommandService;
import com.microservice.crops.crops.domain.services.PestQueryService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import com.microservice.crops.crops.interfaces.REST.resources.CreatePestResource;
import com.microservice.crops.crops.interfaces.REST.resources.PestResource;
import com.microservice.crops.crops.interfaces.REST.transform.PestResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/crops/pests")
@Tag(name = "Pests", description = "Pests Management Endpoints")
public class PestController {

    private final PestCommandService pestCommandService;
    private final PestQueryService pestQueryService;
    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    @Autowired
    private PestRepository pestRepository;

    public PestController(PestCommandService pestCommandService, PestQueryService pestQueryService, CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.pestCommandService = pestCommandService;
        this.pestQueryService = pestQueryService;
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }


    @PostMapping
    public ResponseEntity<PestResource> createPest(@RequestBody CreatePestResource pestResource) {
        Pest pest = new Pest(pestResource.name(),pestResource.description(),pestResource.solution());
        pestCommandService.save(pest);

        PestResource pestResourceResponse = PestResourceFromEntityAssembler.toResourceFromEntity(pest);
        return ResponseEntity.ok(pestResourceResponse);
    }
    @GetMapping
    public ResponseEntity<List<PestResource>> getAllPests() {
        var getAllPestsQuery = new GetAllPestsQuery();
        var pests = pestQueryService.handle(getAllPestsQuery);
        var pestResource = pests.stream().map(PestResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(pestResource);
    }


}