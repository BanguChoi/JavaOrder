package com.javaOrder.manage.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.manage.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    // 추가적인 쿼리 메소드가 필요하면 여기에 정의
}
