package com.microservice.crops.crops.domain.model.commands;

public record CreateDiseaseCommand(String name, String description, String solution) {
}
