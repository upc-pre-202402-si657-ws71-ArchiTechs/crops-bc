package com.microservice.crops.crops.interfaces.REST;

import com.microservice.crops.crops.domain.model.aggregates.Crop;
import com.microservice.crops.crops.domain.model.commands.DeleteCropCommand;
import com.microservice.crops.crops.domain.model.entities.Care;
import com.microservice.crops.crops.domain.model.entities.Disease;
import com.microservice.crops.crops.domain.model.entities.Pest;
import com.microservice.crops.crops.domain.model.queries.GetAllCropsQuery;
import com.microservice.crops.crops.domain.model.queries.GetCropByIdQuery;
import com.microservice.crops.crops.domain.services.CropCommandService;
import com.microservice.crops.crops.domain.services.CropQueryService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.CareRepository;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import com.microservice.crops.crops.interfaces.REST.resources.CreateCropResource;
import com.microservice.crops.crops.interfaces.REST.resources.CropResource;
import com.microservice.crops.crops.interfaces.REST.resources.UpdateCropResource;
import com.microservice.crops.crops.interfaces.REST.transform.CropResourceFromEntityAssembler;
import com.microservice.crops.crops.interfaces.REST.transform.UpdateCropCommandFromResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1/crops")
//@Tag(name = "Crops", description = "Crops Management Endpoints")
public class CropController {

    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    @Autowired
    private CropRepository cropRepository;


    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private PestRepository pestRepository;

    @Autowired
    private CareRepository careRepository;

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }

    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource resource) {
        List<Disease> diseases = resource.diseases().stream()
                .map(diseaseId -> diseaseRepository.findById(Math.toIntExact(diseaseId))
                        .orElseThrow(() -> new NoSuchElementException("Disease not found")))
                .collect(Collectors.toList());

        List<Pest> pests = resource.pests().stream()
                .map(pestId -> pestRepository.findById(Math.toIntExact(pestId))
                        .orElseThrow(() -> new NoSuchElementException("Pest not found")))
                .collect(Collectors.toList());

        List<Care> cares = resource.cares().stream()
                .map(careId -> careRepository.findById(careId)
                        .orElseThrow(() -> new NoSuchElementException("Care not found")))
                .collect(Collectors.toList());

        Crop crop = new Crop(resource.name(), resource.description(), resource.imageUrl(), diseases, pests, cares);
        cropRepository.save(crop);

        CropResource cropResourceResponse = CropResourceFromEntityAssembler.toResourceFromEntity(crop);
        return ResponseEntity.ok(cropResourceResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropResource> getCrop(@PathVariable Long id) {
        return cropQueryService.handle(new GetCropByIdQuery(id))
                .map(crop -> new CropResource(crop.getId(), crop.getName(), crop.getDescription(), crop.getImageUrl(), crop.getDiseaseIds(), crop.getPestIds(),crop.getCareIds()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CropResource>> getAllCrops() {
        var getAllCropsQuery = new GetAllCropsQuery();
        var crops = cropQueryService.handle(getAllCropsQuery);
        var cropResources = crops.stream().map(CropResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cropResources);
    }

    @PutMapping("/{cropId}")
    public ResponseEntity<CropResource> updateCrop(@PathVariable Long cropId, @RequestBody UpdateCropResource updateCropResource) {
        var updateCropCommand = UpdateCropCommandFromResourceAssembler.toCommandFromResource(cropId, updateCropResource);
        var updatedCrop = cropCommandService.handle(updateCropCommand);
        if (updatedCrop.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedCrop.get());
        return ResponseEntity.ok(cropResource);
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<?> deleteCrop(@PathVariable Long cropId) {
        var deleteCropCommand = new DeleteCropCommand(cropId);
        cropCommandService.handle(deleteCropCommand);
        return ResponseEntity.ok("Crop with given id successfully deleted");
    }

}