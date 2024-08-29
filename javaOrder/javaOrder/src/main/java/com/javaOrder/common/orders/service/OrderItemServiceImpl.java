package com.javaOrder.common.orders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.common.orders.domain.OrderItem;
import com.javaOrder.common.orders.repository.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

	private final OrderItemRepository orderItemRepository;
	
	/* 해당 주문 상세 내역 (주문항목) */
	@Override
	public List<OrderItem> orderItemList(OrderItem orderItem) {
		List<OrderItem> orderItemList = orderItemRepository.findByorderNumber(orderItem.getOrderNumber());

		return orderItemList;
	}
}
