package com.javaOrder.common.orders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.orders.repository.OrdersRepository;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

	private final OrdersRepository orderRepository;
	private final MemberRepository memberRepository;
	
	// 모든 회원의 주문내역 리스트 (Admin)
	@Override
	public List<Orders> orderList(Orders orders) {
		List<Orders> orderList = orderRepository.findAll();
		return orderList;
	}
	
	// 해당 회원의 주문내역 리스트 (Member)
	@Override
	public List<Orders> orderClientList(String mCode) {
		Optional<Member> memberOptional = memberRepository.findById(mCode);
		List<Orders> orderList = orderRepository.findBymemberCode(memberOptional.get());
		return orderList;
	}
	
	// 결제 승인 시 주문데이터 삽입 - 결제 쪽에서 추가하는걸로..?
	/*
	public void insertOrder(Orders orders) {
		orderRepository.save(orders);
	}*/
	
	// 주문 데이터 주문 상태 수정
	@Override
	public void updateOrder(Orders orders) {
		Optional<Orders> orderOptional = orderRepository.findById(orders.getOrderNumber());
		Orders updateOrder = orderOptional.get();
		
		updateOrder.setOrderStatus(orders.getOrderStatus());
		orderRepository.save(updateOrder);
	}
	
	/* 주문 데이터 삭제 - 통계에 사용되므로 삭제 불필요 (필요한데가 있나..?)
	 * public void deleteOrder(Orders orders){
	 * 		orderRepository.deleteById(orders.getOrdNum());
	 * }
	 * */
}
