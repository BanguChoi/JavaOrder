package com.javaOrder.admin.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaOrder.admin.product.domain.Product;

public interface ProductService {
    Product createProduct(String categoryCode, String productName, Integer price);
    Page<Product> getProducts(Pageable pageable);
    Product getProductById(String productId);
    Product saveProduct(Product product);
	List<Product> productList(Product product);
}
