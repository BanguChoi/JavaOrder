package com.javaOrder.admin.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaOrder.admin.product.domain.Category;
import com.javaOrder.admin.product.dto.CategoryDTO;
import com.javaOrder.admin.product.service.CategoryService;

@RestController
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            System.out.println("Received CategoryDTO: " + categoryDTO);
            Category newCategory = categoryService.createCategory(categoryDTO.getPrefix(), categoryDTO.getName());
            return ResponseEntity.ok(newCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("카테고리 생성 중 오류가 발생했습니다.");
        }
    }
}
