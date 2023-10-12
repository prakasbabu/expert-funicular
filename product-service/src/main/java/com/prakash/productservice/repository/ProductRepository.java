package com.prakash.productservice.repository;

import com.prakash.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product,String> {
    Product findByName(String productName);

}
