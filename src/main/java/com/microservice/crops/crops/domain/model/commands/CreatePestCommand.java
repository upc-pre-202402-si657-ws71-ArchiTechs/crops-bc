package com.microservice.crops.crops.domain.model.commands;

public record CreatePestCommand(String name, String description, String solution) {
}
