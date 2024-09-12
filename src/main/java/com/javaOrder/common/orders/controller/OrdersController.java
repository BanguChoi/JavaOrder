package com.javaOrder.common.orders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.orders.service.OrdersService;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;
import com.javaOrder.member.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrdersController {

	private final OrdersService orderService;
	
	// 주문내역 전체 조회 (관리자)
	@GetMapping("/admin/orderList")
	public String orderList(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<Orders> orderList = orderService.orderList(pageRequestDTO); 
		model.addAttribute("orderList", orderList);
		return "admin/order/orderList";
	}
	
	// 회원 주문 내역 조회 (회원)
	@GetMapping("/member/orderList/")
	public String orderClientList(HttpSession session, PageRequestDTO pageRequestDTO, Model model) {
		Member member = (Member) session.getAttribute("member");
		
		if(member!=null) {
			PageResponseDTO<Orders> orderList = orderService.orderClientList(member, pageRequestDTO);
			model.addAttribute("orderList", orderList);
			model.addAttribute("searchType", pageRequestDTO.getSearchType());
		    model.addAttribute("keyword", pageRequestDTO.getKeyword());
		    model.addAttribute("status", pageRequestDTO.getStatus());
		    model.addAttribute("startDate", pageRequestDTO.getStartDate());
		    model.addAttribute("endDate", pageRequestDTO.getEndDate());
			return "member/order/orderList";
		}else {
			return "redirect:/member/signin";
		}
	}
	
	// 주문상태 수정 (주문대기(W) / 주문접수(S) / 주문준비완료(P) / 픽업완료(E) / 주문취소(C) : 주문취소는 일단 보류
	@GetMapping("/admin/updateOrders/{ord_num}/{status}")
	public String updateOrder(@PathVariable Long ord_num, @PathVariable String status, Orders orders) {
		orders.setOrderNumber(ord_num);
		orders.setOrderStatus(status);
		
		orderService.updateOrder(orders);
		return "redirect:/admin/orderList";
	}
	
	
}
