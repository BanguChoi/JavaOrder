package com.javaOrder.common.orders.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.member.domain.Member;

public interface OrdersRepository extends JpaRepository<Orders, Long>{
	// 로그인한 회원의 주문내역 리스트 검색
	List<Orders> findByMemberCode(Member mCode);
	// 회원번호로 주문내역 검색
	Page<Orders> findByMemberCode(Member mCode, Pageable pageable);
	
	// 주문상태로 검색
	Page<Orders> findByOrderStatus(String orderStatus, Pageable pageable);
	
	// 주문 날짜로 검색
	Page<Orders> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	
	// 회원
	// 해당 회원의 주문상태별 주문내역 검색
	Page<Orders> findByMemberCodeAndOrderStatus(Member member, String status, Pageable pageable);
	// 해당 회원의 날짜별 주문내역 검색
	Page<Orders> findByMemberCodeAndOrderDateBetween(Member member, LocalDateTime startDate, LocalDateTime endDate,
			Pageable pageable);
	
	// 주문번호로 주문 찾기
//	Optional<Orders> findByOrderNumber(Long partnerOrderId);
	Orders findByOrderNumber(Long partnerOrderId);
	// tid로 주문 찾기
	Orders findByTid(String tid);
	// 회원번호로 가장 많이 주문한 제품 찾기
	Orders findFirstByMemberCode(Member mCode);
}
