package com.prakash.productservice.service.impl;

import com.prakash.productservice.dto.ProductRequest;
import com.prakash.productservice.dto.ProductResponse;
import com.prakash.productservice.model.Product;
import com.prakash.productservice.repository.ProductRepository;
import com.prakash.productservice.service.iface.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();

    productRepository.save(product);
    log.info("Product {} is saved " + product.getId());

    }

    @Override
    public boolean checkForDuplicate(String productName) {
        Product ifExist = productRepository.findByName(productName);
          if (ifExist!=null)
        return true;
          return false;
    }

    @Override
    public List<ProductResponse> getAllProducts() {

       List <Product> products= productRepository.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }
}
