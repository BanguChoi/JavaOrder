package com.javaOrder.common.product.service;

import java.util.List;

import com.javaOrder.common.product.domain.Product;

public interface ProductService {
	List<Product> productList(Product product);
	Product productDetail(Product product);
}
