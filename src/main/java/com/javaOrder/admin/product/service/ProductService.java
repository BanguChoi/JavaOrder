package com.javaOrder.admin.product.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

public interface ProductService {
    Product createProduct(String categoryCode, String productName, Integer price);
    
    Page<Product> getProducts(Pageable pageable);
    
    Product getProductById(String productId);
    
    Product saveProduct(Product product);

	// List<Product> productList(Product product);
	
	// 페이징 처리
	public PageResponseDTO<Product> productList(PageRequestDTO pageRequestDTO);

    // 검색 기능 추가
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
    
    Page<Product> findByCategoryCode(String categoryCode, Pageable pageable);
    
    Page<Product> findByProductDate(LocalDate productDate, Pageable pageable);
}
