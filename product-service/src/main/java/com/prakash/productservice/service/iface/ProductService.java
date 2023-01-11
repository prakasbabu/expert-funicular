package com.prakash.productservice.service.iface;

import com.prakash.productservice.dto.ProductRequest;
import com.prakash.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

     void createProduct(ProductRequest productRequest);

     boolean checkForDuplicate(String productName);

    List<ProductResponse> getAllProducts();
}
