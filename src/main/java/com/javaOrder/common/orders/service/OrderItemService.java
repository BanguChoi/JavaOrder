package com.javaOrder.common.orders.service;

import java.util.List;

import com.javaOrder.common.orders.domain.OrderItem;

public interface OrderItemService {

	List<OrderItem> orderItemList(OrderItem orderItem);

}
