package com.javaOrder.common.orders.orderItem.domain;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.common.orders.domain.OrderItem;
import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.orders.repository.OrderItemRepository;
import com.javaOrder.common.orders.repository.OrdersRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OrderItemTests {
	@Setter(onMethod_=@Autowired)
	private OrderItemRepository orderItemRepository;
	@Setter(onMethod_=@Autowired)
	private OrdersRepository orderRepository;
	
	@Test
	public void orderItemListTest() {
		Optional<Orders> orderOptional = orderRepository.findById(10001L);
		List<OrderItem> orderItemList = orderItemRepository.findByorderNumber(orderOptional.get());
		
		log.info("============주문 상세 내역 리스트===========");
		log.info(orderItemList.toString());
		for(OrderItem orderItem : orderItemList) {
			log.info(orderItem.toString());
			log.info(orderItem.getProductId().toString());
		}
		
		log.info("============주문 상세 내역 리스트===========");
	}
}
