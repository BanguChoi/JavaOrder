package com.javaOrder.admin.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaOrder.admin.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAll(Pageable pageable);
    
    @Query("SELECT MAX(p.productId) FROM Product p WHERE p.productId LIKE :categoryCode%")
    String findMaxProductIdByCategoryCode(String categoryCode);
}
