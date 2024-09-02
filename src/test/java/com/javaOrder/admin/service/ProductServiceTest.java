package com.javaOrder.admin.service;

import com.javaOrder.admin.domain.Category;
import com.javaOrder.admin.domain.Product;
import com.javaOrder.admin.repository.CategoryRepository;
import com.javaOrder.admin.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ProductServiceTest {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // 카테고리 데이터를 사전에 저장
        categoryRepository.save(new Category("A01", "Category A01"));
        categoryRepository.save(new Category("A02", "Category A02"));
        categoryRepository.save(new Category("A03", "Category A03"));
        categoryRepository.save(new Category("B01", "Category B01"));
    }

    @Test
    void testCreateProductsInCategoryA01() {
        // given
        String categoryCode = "A01";
        int price = 0;
        String[] productNames = {
            "A01 Product 1", "A01 Product 2", "A01 Product 3", "A01 Product 4", "A01 Product 5",
            "A01 Product 6", "A01 Product 7", "A01 Product 8", "A01 Product 9", "A01 Product 10"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A01%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A01%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }

    @Test
    void testCreateProductsInCategoryA02() {
        // given
        String categoryCode = "A02";
        int price = 0;
        String[] productNames = {"A02 Product 1", "A02 Product 2"};

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A02%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A02%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }

    @Test
    void testCreateProductsInCategoryA03() {
        // given
        String categoryCode = "A03";
        int price = 0;
        String[] productNames = {
            "A03 Product 1", "A03 Product 2", "A03 Product 3", "A03 Product 4", "A03 Product 5"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A03%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A03%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }

    @Test
    void testCreateProductsInCategoryB01() {
        // given
        String categoryCode = "B01";
        int price = 0;
        String[] productNames = {
            "B01 Product 1", "B01 Product 2", "B01 Product 3", "B01 Product 4", "B01 Product 5", "B01 Product 6"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B01%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B01%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
}
