package com.microservice.crops.crops.interfaces.REST.resources;

import java.util.List;

public record CropResource(Long id, String name, String description, String imageUrl, List<Long> diseases, List<Long> pests,List<Long> cares) {
}
