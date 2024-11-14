package com.microservice.crops.crops.domain.model.entities;

import com.microservice.crops.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Care extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private LocalDate careDate;

    public Care(String description, LocalDate careDate) {
        this.description = description;
        this.careDate = careDate;
    }
    public Care()
    {
    }
}