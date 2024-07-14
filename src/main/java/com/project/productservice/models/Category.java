package com.project.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private long id;
    private String name;
    private String description;
}
