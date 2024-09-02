package com.javaOrder.product.repository;

import com.javaOrder.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    // 추가적인 쿼리 메소드가 필요하면 여기에 정의
}
