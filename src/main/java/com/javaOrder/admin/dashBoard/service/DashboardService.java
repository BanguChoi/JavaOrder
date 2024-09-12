package com.javaOrder.admin.dashBoard.service;

import java.util.List;

import com.javaOrder.admin.dashBoard.vo.CategorySalesDTO;
import com.javaOrder.admin.dashBoard.vo.ProductSalesDTO;
import com.javaOrder.admin.dashBoard.vo.TopProductDTO;
import com.javaOrder.admin.dashBoard.vo.TotalSalesDTO;

public interface DashboardService {
	// 기간별 총 매출 조회
	List<TotalSalesDTO> getTotalSalesList(String period);
	// 기간별 상품별 매출 조회
	List<ProductSalesDTO> getProductSalesList(String period);
	// 기간별 카테고리별 매출 조회
	List<CategorySalesDTO> getCategorySalesList(String period);
	// 상위 5개 잘 팔리는 상품 조회
	List<TopProductDTO> getTop5ProductSalesList(String period);
	// 상위 5개 잘 팔리는 카테고리 조회
	List<CategorySalesDTO> getTop5CategorySalesList(String period);
	
}
