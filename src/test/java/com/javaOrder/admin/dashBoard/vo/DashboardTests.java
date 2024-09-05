//package com.javaOrder.admin.dashBoard.vo;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.javaOrder.admin.dashBoard.repository.DashboardRepository;
//
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Slf4j
//public class DashboardTests {
//	
//	@Setter(onMethod_=@Autowired)
//	private DashboardRepository dashboardRepository;
//	
//	@Test
//	public void weekTest() {
//		log.info("=========주별 매출========");
//		List<DashboardDTO> week = dashboardRepository.findWeeklySales();
//		for(DashboardDTO a:week) {
//			log.info(a.toString());
//		}
//		log.info("=========주별 매출========");
//	}
//}
