package com.javaOrder.admin.dashBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 총 매출 저장 DTO

@Setter
@Getter
@AllArgsConstructor
@ToString
public class TotalSalesDTO {
	private String period;       // 예: '2023-W35' (주별), '2023-08' (월별), '2023-Q1' (분기별) 등
    private Long totalSales;     // 총 매출
}
