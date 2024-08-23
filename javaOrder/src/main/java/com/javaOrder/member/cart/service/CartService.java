package com.javaOrder.member.cart.service;

import java.util.List;

import com.javaOrder.member.cart.domain.Cart;

public interface CartService {
	List<Cart> cartList(Cart cart);
	// void insertCart(Cart cart);
	void updateCart(Cart cart);
	void deleteCart(Cart cart);


}
