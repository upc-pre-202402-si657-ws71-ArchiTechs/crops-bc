package com.microservice.crops.crops.domain.exceptions;

public class CropNotFoundException extends RuntimeException{
    public CropNotFoundException(Long aLong){
        super("Crop with id " + aLong + " not found");
    }
}
