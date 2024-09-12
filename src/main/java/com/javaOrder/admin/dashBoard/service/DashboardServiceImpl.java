package com.javaOrder.admin.dashBoard.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.dashBoard.repository.DashboardRepository;
import com.javaOrder.admin.dashBoard.vo.CategorySalesDTO;
import com.javaOrder.admin.dashBoard.vo.ProductSalesDTO;
import com.javaOrder.admin.dashBoard.vo.TopProductDTO;
import com.javaOrder.admin.dashBoard.vo.TotalSalesDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	private final DashboardRepository dashboardRepository;
	/************** 
	 * 기간별 총 매출
	 **************/
	@Override
	public List<TotalSalesDTO> getTotalSalesList(String period) {
		List<Object[]> results = null;
		List<TotalSalesDTO> totalSalesList = null;
		
		if(period.equals("daily")) {		// 일별
			results = dashboardRepository.findDailyTotalSales();
		}else if(period.equals("week")) {	// 주별
			results = dashboardRepository.findWeeklyTotalSales();
		}else if(period.equals("month")) {	// 월별
			results = dashboardRepository.findMonthlyTotalSales();
		}else if(period.equals("quarter")) {// 분기별
			results = dashboardRepository.findQuarterlyTotalSales();
		}else if(period.equals("year")) {	// 연별
			results = dashboardRepository.findYearlyTotalSales();
		}
		
		totalSalesList = results.stream().map(result->
			new TotalSalesDTO(
				(String) result[0],
				((BigDecimal) result[1]).longValue()
			)
		).collect(Collectors.toList());
		
		return totalSalesList;
	}
	
	/******************* 
	 * 기간에 따른 상품별 매출
	 *******************/
	@Override
	public List<ProductSalesDTO> getProductSalesList(String period) {
		List<Object[]> results = null;
		List<ProductSalesDTO> productSalesList = null;
		
		if(period.equals("daily")) {		// 일별
			results = dashboardRepository.findDailyProductSales();
		}else if(period.equals("week")) {	// 주별
			results = dashboardRepository.findWeeklyProductSales();
		}else if(period.equals("month")) {	// 월별
			results = dashboardRepository.findMonthlyProductSales();
		}else if(period.equals("quarter")) {// 분기별
			results = dashboardRepository.findQuarterlyProductSales();
		}else if(period.equals("year")) {	// 연별
			results = dashboardRepository.findYearlyProductSales();
		}
		
		productSalesList = results.stream().map(result->
			new ProductSalesDTO(
				(String) result[0],						// period
				(String) result[1],						// productName
				((BigDecimal) result[2]).longValue(),	// totalSales
				((BigDecimal) result[2]).longValue()	// quantitySold
			)
		).collect(Collectors.toList());
		
		return productSalesList;
	}
	
	
	/********************** 
	 * 기간에 따른 카테고리별 매출
	 **********************/
	@Override
	public List<CategorySalesDTO> getCategorySalesList(String period) {
		List<Object[]> results = null;
		List<CategorySalesDTO> categorySalesList = null;
		
		if(period.equals("daily")) {		// 일별
			results = dashboardRepository.findDailyCategorySales();
		}else if(period.equals("week")) {	// 주별
			results = dashboardRepository.findWeeklyCategorySales();
		}else if(period.equals("month")) {	// 월별
			results = dashboardRepository.findMonthlyCategorySales();
		}else if(period.equals("quarter")) {// 분기별
			results = dashboardRepository.findQuarterlyCategorySales();
		}else if(period.equals("year")) {	// 연별
			results = dashboardRepository.findYearlyCategorySales();
		}
		
		categorySalesList = results.stream().map(result->
			new CategorySalesDTO(
				(String) result[0],						// period
				(String) result[1],						// productName
				((BigDecimal) result[2]).longValue(),	// totalSales
				((BigDecimal) result[2]).longValue()	// quantitySold
			)
		).collect(Collectors.toList());
		
		return categorySalesList;
	}
	
	/******************* 
	 * 상위 5개 잘팔리는 상품
	 *******************/
	@Override
	public List<TopProductDTO> getTop5ProductSalesList(String period) {
		List<Object[]> results = null;
		List<TopProductDTO> top5ProductSalesList = null;

		if(period.equals("week")) {	// 주별
			results = dashboardRepository.findTop5WeeklyProductSales();
		}else if(period.equals("month")) {	// 월별
			results = dashboardRepository.findTop5MonthlyProductSales();
		}else if(period.equals("quarter")) {// 분기별
			results = dashboardRepository.findTop5QuarterlyProductSales();
		}else if(period.equals("year")) {	// 연별
			results = dashboardRepository.findTop5YearlyProductSales();
		}
		
		top5ProductSalesList = results.stream().map(result->
			new TopProductDTO(
				(String) result[0],						// period
				(String) result[1],						// productName
				((BigDecimal) result[2]).longValue(),	// totalSales
				((BigDecimal) result[3]).longValue()	// quantitySold
			)
		).collect(Collectors.toList());
		
		return top5ProductSalesList;
	}
	
	@Override
	public List<CategorySalesDTO> getTop5CategorySalesList(String period) {
		List<Object[]> results = null;
		List<CategorySalesDTO> top5CategorySalesList = null;

		if(period.equals("week")) {	// 주별
			results = dashboardRepository.findTop5WeeklyCategorySales();
		}else if(period.equals("month")) {	// 월별
			results = dashboardRepository.findTop5MonthlyCategorySales();
		}else if(period.equals("quarter")) {// 분기별
			results = dashboardRepository.findTop5QuarterlyCategorySales();
		}else if(period.equals("year")) {	// 연별
			results = dashboardRepository.findTop5YearlyCategorySales();
		}
		
		top5CategorySalesList = results.stream().map(result->
			new CategorySalesDTO(
				(String) result[0],						// period
				(String) result[1],						// categoryName
				((BigDecimal) result[2]).longValue(),	// totalSales
				((BigDecimal) result[3]).longValue()	// quantitySold
			)
		).collect(Collectors.toList());
		
		return top5CategorySalesList;
	}
}
