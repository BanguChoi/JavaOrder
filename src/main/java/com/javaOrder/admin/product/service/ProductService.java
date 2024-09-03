package com.javaOrder.admin.product.service;

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
}
