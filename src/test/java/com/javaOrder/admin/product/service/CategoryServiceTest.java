package com.javaOrder.admin.product.service;

import com.javaOrder.admin.product.domain.Category;
import com.javaOrder.admin.product.repository.CategoryRepository;
import com.javaOrder.admin.product.service.CategoryService;

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
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        // Setup not needed as we are using actual beans, not mocks
    }

    @Test
    void testCreateCategoryA01() {
        // given
        String prefix = "A";
        String name = "Category A";

        // when
        Category category = categoryService.createCategory(prefix, name);

        // then
        assertNotNull(category);
        assertEquals("A01", category.getCode());
        assertEquals("Category A", category.getName());

        // Verify that the category is actually stored in the database
        Category savedCategory = categoryRepository.findById("A01").orElse(null);
        assertNotNull(savedCategory);
        assertEquals("A01", savedCategory.getCode());
    }

    @Test
    void testCreateCategoryA02() {
        // given
        String prefix = "A";
        String name = "Category A2";

        // when
        Category category = categoryService.createCategory(prefix, name);

        // then
        assertNotNull(category);  // null이 아닌지 확인
        assertEquals("A02", category.getCode());
        assertEquals("Category A2", category.getName());

        // Verify that the category is actually stored in the database
        Category savedCategory = categoryRepository.findById("A02").orElse(null);
        assertNotNull(savedCategory);
        assertEquals("A02", savedCategory.getCode());
    }

    @Test
    void testCreateCategoryB01() {
        // given
        String prefix = "B";
        String name = "Category B";

        // when
        Category category = categoryService.createCategory(prefix, name);

        // then
        assertNotNull(category);
        assertEquals("B01", category.getCode());
        assertEquals("Category B", category.getName());

        // Verify that the category is actually stored in the database
        Category savedCategory = categoryRepository.findById("B01").orElse(null);
        assertNotNull(savedCategory);
        assertEquals("B01", savedCategory.getCode());
    }
}
