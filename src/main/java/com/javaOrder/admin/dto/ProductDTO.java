package com.javaOrder.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String pId;
    private String pName;
    private Double pPrice;
    private String categoryCode;
}
