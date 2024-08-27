package com.javaOrder.common.orders.service;

import java.util.List;

import com.javaOrder.common.orders.domain.Orders;

public interface OrdersService {
	// 주문내역 조회 (관리자)
	public List<Orders> orderList(Orders orders);
	// 주문내역 조회 (회원)
	public List<Orders> orderClientList(String mCode);
	// 주문상태 수정 (관리자)
	public void updateOrder(Orders orders);

}
