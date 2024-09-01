package com.javaOrder.common.orders.service;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;
import com.javaOrder.member.domain.Member;

public interface OrdersService {
	// 주문내역 조회 (관리자)
//	public List<Orders> orderList(Orders orders);
	// 주문내역 조회 (회원)
//	public List<Orders> orderClientList(String mCode);
	// 주문내역 조회 (단일)
	public Orders getOrder(Long ord_num);
	// 주문상태 수정 (관리자)
	public void updateOrder(Orders orders);

	// 페이징 처리
	public PageResponseDTO<Orders> orderList(PageRequestDTO pageRequestDTO);		// 관리자
	public PageResponseDTO<Orders> orderClientList(Member member, PageRequestDTO pageRequestDTO);	// 회원
	
}
