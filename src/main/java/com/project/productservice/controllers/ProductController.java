package com.project.productservice.controllers;

import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Product;
import com.project.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(Long productId) {
        return productService.deleteProduct(productId);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

}
