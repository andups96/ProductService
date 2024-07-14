package com.project.productservice.inheritanceTypes.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value = "3")
public class Student extends User {
    private String batch;
}
