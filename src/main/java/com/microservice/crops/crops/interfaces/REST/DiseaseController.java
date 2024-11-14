package com.microservice.crops.crops.interfaces.REST;

import com.microservice.crops.crops.domain.model.entities.Disease;
import com.microservice.crops.crops.domain.model.queries.GetAllDiseasesQuery;
import com.microservice.crops.crops.domain.services.CropCommandService;
import com.microservice.crops.crops.domain.services.CropQueryService;
import com.microservice.crops.crops.domain.services.DiseaseCommandService;
import com.microservice.crops.crops.domain.services.DiseaseQueryService;
import com.microservice.crops.crops.interfaces.REST.resources.CreateDiseaseResource;
import com.microservice.crops.crops.interfaces.REST.resources.DiseaseResource;
import com.microservice.crops.crops.interfaces.REST.transform.DiseaseResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/crops/diseases")
//@Tag(name = "Diseases", description = "Diseases Management Endpoints")
public class DiseaseController {

    private final DiseaseCommandService diseaseCommandService;
    private final DiseaseQueryService diseaseQueryService;
    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    public DiseaseController(DiseaseCommandService diseaseCommandService, DiseaseQueryService diseaseQueryService, CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.diseaseCommandService = diseaseCommandService;
        this.diseaseQueryService = diseaseQueryService;
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }


    @PostMapping
    public ResponseEntity<DiseaseResource> createDisease(@RequestBody CreateDiseaseResource diseaseResource) {
        Disease disease = new Disease(diseaseResource.name(), diseaseResource.description(), diseaseResource.solution());
        diseaseCommandService.save(disease);

        DiseaseResource diseaseResourceResponse = DiseaseResourceFromEntityAssembler.toResourceFromEntity(disease);
        return ResponseEntity.ok(diseaseResourceResponse);
    }
    @GetMapping
    public ResponseEntity<List<DiseaseResource>> getAllDiseases() {
        var getAllDiseasesQuery = new GetAllDiseasesQuery();
        var diseases = diseaseQueryService.handle(getAllDiseasesQuery);
        var diseaseResource = diseases.stream().map(DiseaseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(diseaseResource);
    }


}