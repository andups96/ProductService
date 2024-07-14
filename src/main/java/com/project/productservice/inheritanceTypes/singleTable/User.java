package com.project.productservice.inheritanceTypes.singleTable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    private long id;
    private String name;
    private String email;
}
