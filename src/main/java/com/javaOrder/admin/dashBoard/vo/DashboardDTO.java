package com.javaOrder.admin.dashBoard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DashboardDTO {
    private String label;   // 예: 주별/월별/분기별/연별 등
    private Double totalSales;
}
