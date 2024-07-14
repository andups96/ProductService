package com.project.productservice.inheritanceTypes.tablePerClass;

import com.project.productservice.inheritanceTypes.joinedTable.User;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name="tpc_instructor")
public class Instructor extends User {
    private String subject;
}
