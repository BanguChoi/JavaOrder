package com.javaOrder.manage.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.javaOrder.manage.product.DTO.ProductDTO;
import com.javaOrder.manage.product.entity.Product;
import com.javaOrder.manage.product.repository.ProductRepository;

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
    public ProductDTO getProductById(String productId) {
        return productRepository.findById(productId)
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
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    // Null 체크를 포함한 convertEntityToDto 메서드
    private ProductDTO convertEntityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setCategoryCode(product.getCategoryCode());
        productDTO.setProductOrder(product.getProductOrder());
        productDTO.setProductExplain(product.getProductExplain());
        productDTO.setProductDate(product.getProductDate());
        productDTO.setProductSell(product.getProductSell());
        productDTO.setProductPrice(product.getProductPrice());  // Null 체크 포함
        productDTO.setProductName(product.getProductName());
        return productDTO;
    }

    private Product convertDtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setCategoryCode(productDTO.getCategoryCode());
        product.setProductOrder(productDTO.getProductOrder());
        product.setProductExplain(productDTO.getProductExplain());
        product.setProductDate(productDTO.getProductDate());
        product.setProductSell(productDTO.getProductSell());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductName(productDTO.getProductName());
        return product;
    }
}
