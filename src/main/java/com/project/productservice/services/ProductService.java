package com.project.productservice.services;

import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(long productId, Product product) throws ProductNotFoundException;

    Product replaceProduct(long productId, Product product) throws ProductNotFoundException;

    void deleteProduct(long productId);

    Product addProduct(Product product);
}
