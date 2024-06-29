package com.project.productservice.services;

import com.project.productservice.models.Product;
import java.util.List;

public interface ProductService {

    Product getProductById(long id);

    List<Product> getAllProducts();
}
