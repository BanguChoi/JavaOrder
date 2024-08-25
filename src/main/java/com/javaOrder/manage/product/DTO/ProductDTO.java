package com.javaOrder.manage.product.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {
    private String productId;
    private String categoryCode;
    private int productOrder;  // 필드명 수정
    private String productExplain;  // 필드명 수정
    private Date productDate;  // 필드명 수정
    private String productSell;  // 필드명 수정
    private Integer productPrice;  // 타입 및 필드명 수정
    private String productName;
}