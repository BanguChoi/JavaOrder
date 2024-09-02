package com.javaOrder.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String prefix;  // 이전에 cateCode로 정의되었던 필드
    private String name;    // 이전에 cateName으로 정의되었던 필드
}
