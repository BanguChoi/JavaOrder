package com.javaOrder.member.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	List<Product> findByCategory(String categroyCode);

}

