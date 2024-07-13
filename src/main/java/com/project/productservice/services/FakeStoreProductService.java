package com.project.productservice.services;

import com.project.productservice.dto.FakeStoreProductDto;
import com.project.productservice.models.Category;
import com.project.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;

//https://fakestoreapi.com/docs

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

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    //Partial Update
    @Override
    public Product updateProduct(long productId, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + productId,
                HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(response);

    }

    @Override
    public Product replaceProduct(long productId, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + productId,
                HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(response);
    }

    @Override
    public Product deleteProduct(long productId) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(null);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + productId,
                HttpMethod.DELETE, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(response);
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
}
