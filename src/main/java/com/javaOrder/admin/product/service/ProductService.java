package com.javaOrder.admin.product.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.common.util.vo.ProductPageRequestDTO;
import com.javaOrder.common.util.vo.ProductPageResponseDTO;

import jakarta.servlet.http.HttpSession;

public interface ProductService {

    // 제품 생성 메서드, productExplain 추가
    Product createProduct(String categoryCode, String productName, Integer price, String productExplain);
    
    // 페이징 처리된 제품 목록 조회
    Page<Product> getProducts(Pageable pageable);
    
    // 특정 제품을 ID로 조회
    Product getProductById(String productId);
    
    // 제품 저장 (수정/등록 모두 처리)
    Product saveProduct(Product product);

    // 검색 기능: 제품 이름으로 검색
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
    
    // 카테고리 코드로 제품 검색
    Page<Product> findByCategoryCode(String categoryCode, Pageable pageable);
    
    // 등록일자로 제품 검색
    Page<Product> findByProductDate(LocalDate productDate, Pageable pageable);
    
	// 제품리스트 페이징/카테고리 처리
	ProductPageResponseDTO<Product> productList(ProductPageRequestDTO productPageRequestDTO, HttpSession session);
}
