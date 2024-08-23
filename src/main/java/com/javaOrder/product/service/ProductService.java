package com.javaOrder.product.service;

import java.util.List;

import com.javaOrder.product.DTO.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(String p_id);
    void addProduct(ProductDTO product);
    void updateProduct(ProductDTO product);
    void deleteProduct(String p_id);
}
