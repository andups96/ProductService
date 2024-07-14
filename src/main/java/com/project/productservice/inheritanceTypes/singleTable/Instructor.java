package com.project.productservice.inheritanceTypes.singleTable;

import com.project.productservice.inheritanceTypes.joinedTable.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private String subject;
}
