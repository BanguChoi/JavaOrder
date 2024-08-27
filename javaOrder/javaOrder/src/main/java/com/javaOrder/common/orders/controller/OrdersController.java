package com.javaOrder.common.orders.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.orders.service.OrdersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/javaOrder/*")
@RequiredArgsConstructor
public class OrdersController {

	private final OrdersService orderService;
	
	// 주문내역 전체 조회 (관리자)
	@GetMapping("/admin/orderList")
	public String orderList(Orders orders, Model model) {
		List<Orders> orderList = orderService.orderList(orders);
		model.addAttribute("orderList", orderList);
		return "admin/orderList";
	}
	
	// 회원 주문 내역 조회 (회원)
	@GetMapping("/member/orderList")
	public String orderClientList(@PathVariable String mCode, Model model) {
		List<Orders> orderList = orderService.orderClientList(mCode);
		model.addAttribute("orderList", orderList);
		return "member/orderList";
	}
	
	// 주문상태 수정 (주문대기(W) / 주문접수(S) / 주문준비완료(P) / 픽업완료(E) / 주문취소(C) : 주문취소는 일단 보류
	@GetMapping("/admin/updateOrders/{ord_num}")
	public String updateOrder(@PathVariable Long ord_num, Orders orders) {
		orderService.updateOrder(orders);
		return "redirect:/admin/orderList";
	}
	
	
}
