package com.javaOrder.admin.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    
    @Id
    @Column(name = "cate_code", length = 3)
    private String code;

    @Column(name = "cate_name", nullable = false, length = 50)
    private String name;

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
