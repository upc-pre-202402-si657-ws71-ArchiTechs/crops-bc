package com.microservice.crops.crops.interfaces.REST.resources;

import java.time.LocalDate;

public record CreateCareResource(String description, LocalDate careDate) {
}