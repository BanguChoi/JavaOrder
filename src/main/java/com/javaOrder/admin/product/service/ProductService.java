package com.javaOrder.admin.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaOrder.admin.product.DTO.ProductDTO;

public interface ProductService {
    Page<ProductDTO> getAllProducts(Pageable pageable);
    ProductDTO getProductById(String productId);
    void addProduct(ProductDTO product);
    void updateProduct(ProductDTO product);
    void deleteProduct(String productid);
}
