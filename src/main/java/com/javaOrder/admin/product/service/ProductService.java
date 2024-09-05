package com.javaOrder.admin.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaOrder.admin.product.domain.Product;

import java.time.LocalDate;

public interface ProductService {
    Product createProduct(String categoryCode, String productName, Integer price);
    
    Page<Product> getProducts(Pageable pageable);
    
    Product getProductById(String productId);
    
    Product saveProduct(Product product);

    // 검색 기능 추가
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
    
    Page<Product> findByCategoryCode(String categoryCode, Pageable pageable);
    
    Page<Product> findByProductDate(LocalDate productDate, Pageable pageable);
}
