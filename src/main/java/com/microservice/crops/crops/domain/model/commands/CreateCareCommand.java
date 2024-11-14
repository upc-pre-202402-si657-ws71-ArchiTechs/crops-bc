package com.microservice.crops.crops.domain.model.commands;

import java.time.LocalDate;

public record CreateCareCommand(String description, LocalDate careDate) {
}