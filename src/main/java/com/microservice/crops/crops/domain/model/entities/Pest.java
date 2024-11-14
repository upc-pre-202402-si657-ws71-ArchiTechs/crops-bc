package com.microservice.crops.crops.domain.model.entities;

import com.microservice.crops.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class Pest extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String Name;

    @NotNull
    @Size(max = 500)
    private String Description;

    @NotNull
    @Size(max = 500)
    private String Solution;



    public Pest(String Name, String Description, String Solution){
        this.Name = Name;
        this.Description = Description;
        this.Solution = Solution;
    }
    public Pest(){

    }

}