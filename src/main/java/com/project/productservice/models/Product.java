package com.project.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
        private long id;
        private String title;
        private double price;
        private Category category;
}
