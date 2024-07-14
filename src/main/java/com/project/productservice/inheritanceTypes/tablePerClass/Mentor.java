package com.project.productservice.inheritanceTypes.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name="tpc_mentor")
public class Mentor extends User {
    private String subject;
}
