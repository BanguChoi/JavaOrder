package com.javaOrder.member.cart.service;

import java.util.List;

import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.domain.Member;

public interface CartItemService {
	List<CartItem> cartItemList(Member member);
	void updateCartItem(String itemId, int itemNum);
	void deleteCartItem(String itemId);
	CartItem insertCartItem(String cartId, CartItem cartItem);

}
