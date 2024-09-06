package com.javaOrder.admin.product.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.common.util.vo.ProductPageRequestDTO;
import com.javaOrder.common.util.vo.ProductPageResponseDTO;

import jakarta.servlet.http.HttpSession;

public interface ProductService {
    Product createProduct(String categoryCode, String productName, Integer price);
    
    Page<Product> getProducts(Pageable pageable);
    
    Product getProductById(String productId);
    
    Product saveProduct(Product product);

	// List<Product> productList(Product product);

    // 검색 기능 추가
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
    
    Page<Product> findByCategoryCode(String categoryCode, Pageable pageable);
    
    Page<Product> findByProductDate(LocalDate productDate, Pageable pageable);
    
	// 제품리스트 페이징/카테고리 처리
	ProductPageResponseDTO<Product> productList(ProductPageRequestDTO productPageRequestDTO, HttpSession session);

}
