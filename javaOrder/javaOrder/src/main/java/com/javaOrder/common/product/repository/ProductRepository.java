package com.javaOrder.common.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.common.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
}

