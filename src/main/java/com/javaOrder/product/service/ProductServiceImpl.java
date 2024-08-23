package com.javaOrder.product.service;

import com.javaOrder.product.DTO.ProductDTO;
import com.javaOrder.product.entity.Product;
import com.javaOrder.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(String p_id) {
        return productRepository.findById(p_id)
                .map(this::convertEntityToDto)
                .orElse(null);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = convertDtoToEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = convertDtoToEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String p_id) {
        productRepository.deleteById(p_id);
    }

    private ProductDTO convertEntityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setP_id(product.getP_id());
        productDTO.setCate_code(product.getCate_code());
        productDTO.setP_order(product.getP_order());
        productDTO.setP_ex(product.getP_ex());
        productDTO.setP_date(product.getP_date());
        productDTO.setP_sell(product.getP_sell());
        productDTO.setP_price(product.getP_price());
        productDTO.setP_name(product.getP_name());
        return productDTO;
    }

    private Product convertDtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setP_id(productDTO.getP_id());
        product.setCate_code(productDTO.getCate_code());
        product.setP_order(productDTO.getP_order());
        product.setP_ex(productDTO.getP_ex());
        product.setP_date(productDTO.getP_date());
        product.setP_sell(productDTO.getP_sell());
        product.setP_price(productDTO.getP_price());
        product.setP_name(productDTO.getP_name());
        return product;
    }
}
