package com.javaOrder.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaOrder.admin.domain.Product;

public interface ProductService {
    Product createProduct(String categoryCode, String productName, Integer price);
    Page<Product> getProducts(Pageable pageable);
    Product getProductById(String productId);
    Product saveProduct(Product product);
}
