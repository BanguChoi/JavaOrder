package com.javaOrder.admin.product.service;

import com.javaOrder.admin.product.domain.Category;
import com.javaOrder.admin.product.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(String prefix, String name) {
        String maxCode = categoryRepository.findMaxCategoryCodeByPrefix(prefix);

        String newCode;
        if (maxCode == null) {
            newCode = prefix + "01";  // 첫 번째 카테고리 코드
        } else {
            int codeNumber = Integer.parseInt(maxCode.substring(1)) + 1;
            newCode = String.format("%s%02d", prefix, codeNumber);  // 다음 카테고리 코드
        }

        Category category = new Category(newCode, name);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
