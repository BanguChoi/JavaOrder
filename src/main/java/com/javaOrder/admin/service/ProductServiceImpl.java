package com.javaOrder.admin.service;

import com.javaOrder.admin.domain.Category;
import com.javaOrder.admin.domain.Product;
import com.javaOrder.admin.repository.CategoryRepository;
import com.javaOrder.admin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Product createProduct(String categoryCode, String productName, Integer price) {
        // 카테고리 코드로 카테고리 엔티티 조회
        Category category = categoryRepository.findById(categoryCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category code: " + categoryCode));

        // 해당 카테고리 코드로 시작하는 상품 코드 중 가장 큰 값을 조회
        String maxProductCode = productRepository.findMaxProductCodeByCategoryCode(categoryCode);

        // 새로운 상품 코드 생성
        String newProductCode;
        if (maxProductCode == null) {
            newProductCode = categoryCode + "01";  // 첫 번째 상품 코드
        } else {
            // 마지막 상품 코드의 숫자 부분을 증가시킴
            int codeNumber = Integer.parseInt(maxProductCode.substring(categoryCode.length())) + 1;
            newProductCode = String.format("%s%02d", categoryCode, codeNumber);
        }

        // 새로운 상품 엔티티 생성 및 저장
        Product product = new Product();
        product.setProductId(newProductCode);
        product.setCategory(category);
        product.setProductName(productName);
        product.setProductPrice(price);  // 가격은 null일 수 있음

        // 상품을 저장하고 반환
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
