package com.prakash.productservice.controller;

import com.prakash.productservice.dto.ProductRequest;
import com.prakash.productservice.dto.ProductResponse;
import com.prakash.productservice.service.iface.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        if ( !productService.checkForDuplicate(productRequest.getName())){
            productService.createProduct(productRequest);
        }else{
           log.info("Duplicate Product");
        }
    }

    @GetMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){

       return  productService.getAllProducts();
    }


}
