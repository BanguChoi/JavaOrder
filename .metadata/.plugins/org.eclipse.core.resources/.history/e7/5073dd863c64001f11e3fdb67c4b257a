package com.javaOrder.common.orders.orderitem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.orders.orderitem.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>{
	List<OrderItem> findByorderNumber(Orders orderNumber);
}
