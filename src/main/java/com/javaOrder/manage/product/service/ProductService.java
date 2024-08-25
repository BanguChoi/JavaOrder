package com.javaOrder.manage.product.service;

import java.util.List;

import com.javaOrder.manage.product.DTO.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(String productId);
    void addProduct(ProductDTO product);
    void updateProduct(ProductDTO product);
    void deleteProduct(String productid);
}
