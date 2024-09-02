package com.javaOrder.member.cart.service;

import com.javaOrder.member.cart.domain.Cart;

public interface CartService {
	void updateCart(int cartPrice, String memberCode);
	void deleteCart(Cart cart);
	Cart createCart(String memberCode);
	Cart getCartByMemberCode(String memberCode);
}
