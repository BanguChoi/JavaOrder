package com.javaOrder.member.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    // 추가적인 쿼리 메소드가 필요하면 여기에 정의
}

