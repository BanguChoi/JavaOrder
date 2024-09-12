package com.javaOrder.admin.dashBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 상위 5개 잘팔리는 상품 DTO

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TopProductDTO {
    private String period;        // 기간 (일별, 주별, 월별 등)
    private String productName;   // 상품명
    private Long totalSales;      // 총 매출
    private Long quantitySold;    // 팔린 개수
}
