package com.project.productservice.inheritanceTypes.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private String subject;
}
