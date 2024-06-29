package com.project.productservice.services;

import com.project.productservice.configurations.ApplicationConfiguration;
import com.project.productservice.dto.FakeStoreProductDto;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long productId) {
        //Call FakeStoreProductService to fetch product by id
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    private static Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        //Convert FakeStoreProductDto to Product
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }
}
