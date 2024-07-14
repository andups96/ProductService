package com.project.productservice.inheritanceTypes.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name="tpc_student")
public class Student extends User {
    private String batch;
}
