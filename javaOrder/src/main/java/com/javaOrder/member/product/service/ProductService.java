package com.javaOrder.member.product.service;

import java.util.List;

import com.javaOrder.member.product.domain.Product;

public interface ProductService {
	List<Product> productList(Product product);
	Product productDetail(Product product);
}
