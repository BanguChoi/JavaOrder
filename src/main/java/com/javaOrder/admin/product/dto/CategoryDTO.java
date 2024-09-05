package com.javaOrder.admin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String prefix;  // 이전에 cateCode로 정의되었던 필드
    private String name;    // 이전에 cateName으로 정의되었던 필드
    private boolean isSelected; // 현재 선택된 카테고리인지 여부
    
    public CategoryDTO(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
        this.isSelected = false;
    }
}
