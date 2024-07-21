package com.project.productservice.repositories;

import com.project.productservice.models.Product;
import com.project.productservice.projections.ProductWithTitleAndId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    //HQL Query
    @Query("select p.id as id, p.title as title from Product p where p.id = :id")
    List<ProductWithTitleAndId> findProductTitleById(Long id);

    //SQL Query
    @Query(value = "select * from product where id = :id", nativeQuery = true)
    List<Product> findProductTitleByIdNative(Long id);

}
