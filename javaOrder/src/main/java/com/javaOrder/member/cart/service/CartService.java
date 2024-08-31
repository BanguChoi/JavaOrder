package com.javaOrder.member.cart.service;

import com.javaOrder.member.cart.domain.Cart;

public interface CartService {
	// Optional<Cart> cartList(Member member);
	void updateCart(Cart cart);
	void deleteCart(Cart cart);
	Cart createCart(String memberCode);
	Cart getCartByMemberCode(String memberCode);


}
