package com.javaOrder.admin.dashBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.dashBoard.repository.DashboardRepository;
import com.javaOrder.admin.dashBoard.vo.DashboardDTO;

@Service
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	private DashboardRepository dashboardRepository;
	
	// 주별 매출
	@Override
	public List<DashboardDTO> getWeeklySales() {
	    return dashboardRepository.findWeeklySales();
	}
	
	// 월별 매출
	@Override
	public List<DashboardDTO> getMonthlySales() {
	    return dashboardRepository.findMonthlySales();
	}
	
	// 분기별 매출
	@Override
	public List<DashboardDTO> getQuarterlySales() {
	    return dashboardRepository.findQuarterlySales();
	}
	
	// 연별 매출
	@Override
	public List<DashboardDTO> getYearlySales() {
	    return dashboardRepository.findYearlySales();
	}
	
	// 상품별 매출
	@Override
	public List<DashboardDTO> getProductSales() {
	    return dashboardRepository.findProductSales();
	}
	
	// 상위 5개 상품
	@Override
	public List<DashboardDTO> getTop5Products() {
	    return dashboardRepository.findTop5Products();
	}
}
