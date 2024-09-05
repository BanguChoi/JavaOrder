package com.javaOrder.admin.dashBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaOrder.admin.dashBoard.service.DashboardService;
import com.javaOrder.admin.dashBoard.vo.DashboardDTO;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // 주별 매출
    @GetMapping("/dashboard/sales/weekly")
    public String getWeeklySales(Model model) {
        List<DashboardDTO> weeklySales = dashboardService.getWeeklySales();
        model.addAttribute("weeklySales", weeklySales);
        return "dashboard/weeklySales"; // Thymeleaf 템플릿: weeklySales.html
    }

    // 월별 매출
    @GetMapping("/dashboard/sales/monthly")
    public String getMonthlySales(Model model) {
        List<DashboardDTO> monthlySales = dashboardService.getMonthlySales();
        model.addAttribute("monthlySales", monthlySales);
        return "dashboard/monthlySales"; // Thymeleaf 템플릿: monthlySales.html
    }

    // 분기별 매출
    @GetMapping("/dashboard/sales/quarterly")
    public String getQuarterlySales(Model model) {
        List<DashboardDTO> quarterlySales = dashboardService.getQuarterlySales();
        model.addAttribute("quarterlySales", quarterlySales);
        return "dashboard/quarterlySales"; // Thymeleaf 템플릿: quarterlySales.html
    }

    // 상위 5개 상품
    @GetMapping("/dashboard/sales/top5")
    public String getTop5Products(Model model) {
        List<DashboardDTO> top5Products = dashboardService.getTop5Products();
        model.addAttribute("top5Products", top5Products);
        return "dashboard/top5Products"; // Thymeleaf 템플릿: top5Products.html
    }
}
