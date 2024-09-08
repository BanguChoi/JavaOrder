package com.javaOrder.admin.dashBoard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaOrder.admin.dashBoard.service.DashboardService;
import com.javaOrder.admin.dashBoard.vo.CategorySalesDTO;
import com.javaOrder.admin.dashBoard.vo.ProductSalesDTO;
import com.javaOrder.admin.dashBoard.vo.TopProductDTO;
import com.javaOrder.admin.dashBoard.vo.TotalSalesDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    
    /*****************************************
     * 기간별 총 매출
     *****************************************/
    @GetMapping("/admin/totalSales")
    public ResponseEntity<List<TotalSalesDTO>> getTotalSales(@RequestParam("period") String period) {
    	List<TotalSalesDTO> totalSales = dashboardService.getTotalSalesList(period);
    	
    	return ResponseEntity.ok(totalSales);
    }
    
    /*****************************************
     * 상품별 매출
     *****************************************/
    @GetMapping("/admin/productSales")
    public ResponseEntity<List<ProductSalesDTO>> getProductSales(@RequestParam("period") String period) {
    	List<ProductSalesDTO> productSales = dashboardService.getProductSalesList(period);
    	
    	return ResponseEntity.ok(productSales);
    }
    
    /*****************************************
     * 카테고리별 총 매출
     *****************************************/
    @GetMapping("/admin/categorySales")
    public ResponseEntity<List<CategorySalesDTO>> getCategorySales(@RequestParam("period") String period) {
    	List<CategorySalesDTO> categorySales = dashboardService.getCategorySalesList(period);
    	
    	return ResponseEntity.ok(categorySales);
    }
    
    /*****************************************
     * 상위5개 많이 팔린 상품
     *****************************************/
    @GetMapping("/admin/topVProductSales")
    public ResponseEntity<List<TopProductDTO>> getTopVProductSales(@RequestParam("period") String period) {
    	List<TopProductDTO> topVProductSales = dashboardService.getTop5ProductSalesList(period);
    	
    	return ResponseEntity.ok(topVProductSales);
    }
    
    /*****************************************
     * 상위5개 많이 팔린 카테고리
     *****************************************/
    @GetMapping("/admin/topVCategorySales")
    public ResponseEntity<List<CategorySalesDTO>> getTopVCategorySales(@RequestParam("period") String period) {
    	List<CategorySalesDTO> topVProductSales = dashboardService.getTop5CategorySalesList(period);
    	
    	return ResponseEntity.ok(topVProductSales);
    }
}
