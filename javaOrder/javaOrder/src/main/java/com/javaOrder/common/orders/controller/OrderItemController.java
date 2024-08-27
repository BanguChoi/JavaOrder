package com.javaOrder.common.orders.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.common.orders.domain.OrderItem;
import com.javaOrder.common.orders.service.OrderItemService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/orderItem/*")
@RequiredArgsConstructor
public class OrderItemController {
	
	private final OrderItemService orderItemService;
	
	// 주문 상세 내역 (주문항목 / 관리자)
	@GetMapping("/admin/orderItemList/{ord_num}")
	public String orderItemList(@PathVariable Long ord_num, OrderItem orderItem, Model model) {
		List<OrderItem> orderItemList = orderItemService.orderItemList(orderItem);
		model.addAttribute("orderItemList", orderItemList);
		return "admin/orderItem/orderItemList";
	}
	
	@GetMapping("/member/orderItemList/{ord_num}")
	public String orderItemMemberList(@PathVariable Long ord_num, OrderItem orderItem, Model model) {
		List<OrderItem> orderItemList = orderItemService.orderItemList(orderItem);
		model.addAttribute("orderItemList", orderItemList);
		return "admin/orderItem/orderItemList";
	}
}
