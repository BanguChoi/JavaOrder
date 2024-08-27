package com.javaOrder.common.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaOrder.common.product.domain.Product;
import com.javaOrder.common.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	
	/* 제품 리스트 */
	@Override
	public List<Product> productList(Product product) {
		List<Product> productList = null;
		productList = (List<Product>) productRepository.findAll();
		return productList;
	}
	
	
	/* 제품 상세페이지 */
	@Override
	public Product productDetail(Product product) {
		Optional<Product> productOptional = productRepository.findById(product.getProductId());
		Product detail = productOptional.orElseThrow();
		return detail;
	}
	
}
