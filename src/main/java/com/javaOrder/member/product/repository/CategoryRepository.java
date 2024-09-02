package com.javaOrder.member.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.product.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
