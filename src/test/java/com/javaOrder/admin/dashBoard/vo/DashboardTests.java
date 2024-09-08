package com.javaOrder.admin.dashBoard.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.admin.dashBoard.repository.DashboardRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DashboardTests {
	
	@Setter(onMethod_=@Autowired)
	private DashboardRepository dashboardRepository;
	
	@Test
	public void weekTest() {
		log.info("=========일별 매출========");
		List<Object[]> totalSalesList = dashboardRepository.findDailyTotalSales();
		List<TotalSalesDTO> totalSalesDailyList = totalSalesList.stream().map(result -> 
        	new TotalSalesDTO(
        		(String) result[0], // period
        		((BigDecimal) result[1]).longValue()  	// totalSales
        	)
		).collect(Collectors.toList());
		
		for(TotalSalesDTO daily : totalSalesDailyList) {
			log.info(daily.toString());
		}

//		log.info("=========주별 매출========");
//		List<TotalSalesDTO> totalSalesWeeklyList = dashboardRepository.findDailyTotalSales();
//		for(TotalSalesDTO week : totalSalesWeeklyList) {
//			log.info(week.toString());
//		}
		
		log.info("=========일단 여기까지========");
	}
}
