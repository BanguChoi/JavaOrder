package com.javaOrder.common.orders.orderitem.service;

import java.util.List;

import com.javaOrder.common.orders.orderitem.domain.OrderItem;

public interface OrderItemService {

	List<OrderItem> orderItemList(OrderItem orderItem);

}
