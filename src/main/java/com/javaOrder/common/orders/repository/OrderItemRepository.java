package com.javaOrder.common.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaOrder.common.orders.domain.OrderItem;
import com.javaOrder.common.orders.domain.Orders;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>{
	List<OrderItem> findByorderNumber(Orders orderNumber);
	
	@Query("SELECT p.productId, p.productName, SUM(oi.orderitemNumber) AS orderitemNumber " +
		       "FROM OrderItem oi " +
		       "JOIN oi.orderNumber o " +
		       "JOIN oi.productId p " +
		       "WHERE o.memberCode.memberCode = :memberCode " +
		       "GROUP BY p.productId, p.productName " +
		       "ORDER BY orderitemNumber FETCH FIRST 3 ROWS ONLY")
	List<Object[]> findTop3ProductsByMemberCode(@Param("memberCode")String memberCode);
}
