package com.javaOrder.common.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.member.domain.Member;

public interface OrdersRepository extends JpaRepository<Orders, Long>{
	List<Orders> findBymemberCode(Member mCode);
}
