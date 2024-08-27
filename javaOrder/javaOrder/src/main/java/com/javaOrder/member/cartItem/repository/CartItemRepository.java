package com.javaOrder.member.cartItem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cartItem.domain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, String>{
	List<CartItem> findByCart(Cart cart);
}
