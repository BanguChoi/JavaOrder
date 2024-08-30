package com.javaOrder.common.orders.domain;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.common.orders.repository.OrdersRepository;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OrdersTests {

	@Setter(onMethod_=@Autowired)
	private OrdersRepository orderRepository;
	
	@Setter(onMethod_=@Autowired)
	private MemberRepository memberRepository;
	
	@Test
	public void orderListTest() {
		List<Orders> orderList = orderRepository.findAll();
		log.info("==========주문 리스트 출력 (관리자)==========");
		for(Orders order : orderList) {
			log.info(order.toString());
		}
		log.info("==========주문 리스트 출력 (관리자)==========");
	}
	
	@Test
	public void orderClientListTest() {
		Optional<Member> memberOptional = memberRepository.findById("M041");
		List<Orders> orderList = orderRepository.findByMemberCode(memberOptional.get());
		log.info("===========주문 리스트 출력 (회원)============");
		for(Orders order:orderList) {
			log.info(order.toString());
		}
		log.info("===========주문 리스트 출력 (회원)============");
	}
	
	@Test
	public void updateOrderTest() {
		log.info("===========주문 상태 출력 (관리자)============");
		Optional<Orders> orderOptional = orderRepository.findById(10002L);
		Orders updateOrder = orderOptional.get();
		
		updateOrder.setOrderStatus("W");
		orderRepository.save(updateOrder);
		log.info("===========주문 상태 출력 (관리자)============");
	}
}
