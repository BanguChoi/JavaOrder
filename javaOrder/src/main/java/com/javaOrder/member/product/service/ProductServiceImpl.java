package com.javaOrder.member.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.member.product.domain.Product;
import com.javaOrder.member.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	/* 제품 리스트 */
	@Override
	public List<Product> productList(Product product) {
		List<Product> productList = productRepository.findAll();
		return productList;
	}


	/* 제품 상세페이지 */
	@Override
	public Product getProductById(String productId) {
		return productRepository.findById(productId).orElseThrow();

	}
	

	
	
}
