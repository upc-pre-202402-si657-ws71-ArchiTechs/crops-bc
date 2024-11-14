package com.microservice.crops.crops.application.internal.commandservices;

import com.microservice.crops.crops.domain.model.aggregates.Crop;
import com.microservice.crops.crops.domain.model.commands.CreateCropCommand;
import com.microservice.crops.crops.domain.model.commands.DeleteCropCommand;
import com.microservice.crops.crops.domain.model.commands.UpdateCropCommand;
import com.microservice.crops.crops.domain.model.entities.Care;
import com.microservice.crops.crops.domain.model.entities.Disease;
import com.microservice.crops.crops.domain.model.entities.Pest;
import com.microservice.crops.crops.domain.services.CropCommandService;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.CareRepository;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import com.microservice.crops.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CropCommandServiceImpl implements CropCommandService {

    private final CropRepository cropRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private PestRepository pestRepository;

    @Autowired
    private CareRepository careRepository;

    @Autowired
    public CropCommandServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }
    @Override
    public Long handle(CreateCropCommand command) {
        List<Disease> diseases = command.diseases().stream()
                .map(diseaseId -> diseaseRepository.findById(Math.toIntExact(diseaseId))
                        .orElseThrow(() -> new NoSuchElementException("Disease not found")))
                .collect(Collectors.toList());

        List<Pest> pests = command.pests().stream()
                .map(pestId -> pestRepository.findById(Math.toIntExact(pestId))
                        .orElseThrow(() -> new NoSuchElementException("Pest not found")))
                .collect(Collectors.toList());

        List<Care> cares = command.cares().stream()
                .map(careId -> careRepository.findById(careId)
                        .orElseThrow(() -> new NoSuchElementException("Care not found")))
                .collect(Collectors.toList());

        Crop crop = new Crop(command.name(), command.description(), command.imageUrl(), diseases, pests, cares);
        crop = cropRepository.save(crop);
        return crop.getId();
    }

    @Override
    public Optional<Crop> handle(UpdateCropCommand command) {
        return cropRepository.findById(command.cropId()).map(existingCrop -> {
            existingCrop.setName(command.name());
            existingCrop.setDescription(command.description());
            return cropRepository.save(existingCrop);
        });
    }

    @Override
    public void handle(DeleteCropCommand command) {
        cropRepository.deleteById(command.cropId());
    }

    @Override
    public void save(Crop crop) {
        cropRepository.save(crop);
    }
}