package com.javaOrder.admin.service;

import com.javaOrder.admin.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product createProduct(String categoryCode, String productName, Integer price);
    Page<Product> getProducts(Pageable pageable);
    Product getProductById(String productId);
    Product saveProduct(Product product);
}
