package com.microservice.crops.crops.interfaces.REST.resources;

import java.time.LocalDate;

public record CareResource(Long id, String description, LocalDate careDate) {
}
