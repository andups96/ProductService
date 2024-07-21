package com.project.productservice.services;

import com.project.productservice.exceptions.ProductNotFoundException;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import com.project.productservice.repositories.CategoryRepository;
import com.project.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product getProductById(long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Patch
    @Override
    public Product updateProduct(long productId, Product product) throws ProductNotFoundException {
        Product productInDB = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));
        if (null != product.getTitle())
            productInDB.setTitle(product.getTitle());
        if (null != product.getPrice())
            productInDB.setPrice(product.getPrice());
        return productRepository.save(productInDB);
    }

    //Put
    @Override
    public Product replaceProduct(long productId, Product product) throws ProductNotFoundException {
        if (productRepository.existsById(productId)) {
            product.setId(productId);
            return productRepository.save(product);
        }
        else
            throw new ProductNotFoundException("Product with id " + productId + " not found");
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();
        if (null == category.getId()) {
            category = categoryRepository.save(product.getCategory());
            product.setCategory(category);
        }
        return productRepository.save(product);
    }
}
