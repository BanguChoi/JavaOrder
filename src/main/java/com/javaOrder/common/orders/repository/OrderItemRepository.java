package com.javaOrder.common.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.common.orders.domain.OrderItem;
import com.javaOrder.common.orders.domain.Orders;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>{
	List<OrderItem> findByorderNumber(Orders orderNumber);
}
