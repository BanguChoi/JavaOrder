package com.javaOrder.admin.product.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaOrder.admin.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    // 카테고리 코드로 시작하는 상품 코드 중 가장 큰 값을 조회하는 메서드
    @Query("SELECT MAX(p.productId) FROM Product p WHERE p.category.code = :cateCode")
    String findMaxProductCodeByCategoryCode(@Param("cateCode") String cateCode);

    // 상품명을 부분 일치 검색하는 메서드
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    // 카테고리 코드를 기준으로 검색하는 메서드
    Page<Product> findByCategory_Code(String categoryCode, Pageable pageable);

    // 등록일자를 기준으로 검색하는 메서드
    Page<Product> findByProductDate(LocalDate productDate, Pageable pageable);

    // 상품명을 부분 일치 검색하고, 카테고리 코드로도 검색하는 메서드
	Page<Product> findByProductNameContainingAndCategory_Code(String keyWord, String categoryCode, Pageable pageable);
}
