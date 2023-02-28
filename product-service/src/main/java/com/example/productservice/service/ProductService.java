package com.example.productservice.service;

import com.example.productservice.domain.Product;
import com.example.productservice.dto.ProductRequest;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public String addProduct(ProductRequest productRequest);
}
