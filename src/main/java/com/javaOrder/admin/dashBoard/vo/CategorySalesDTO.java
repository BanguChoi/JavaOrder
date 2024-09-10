package com.javaOrder.admin.dashBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 카테고리별 매출 저장 DTO

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategorySalesDTO {
    private String period;         // 기간 (일별, 주별, 월별 등)
    private String categoryName;   // 카테고리명
    private Long quantitySold;     // 팔린 개수
    private Long totalSales;       // 총 매출
}
