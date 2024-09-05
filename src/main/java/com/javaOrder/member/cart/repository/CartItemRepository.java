package com.javaOrder.member.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.domain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, String>{
	List<CartItem> findByCart(Cart cart);

//	void deleteByCart(Cart cart);
	@Modifying
	@Query("DELETE FROM CartItem c WHERE c.cart = :cart")
	void deleteByCart(@Param("cart") Cart cart);

}
