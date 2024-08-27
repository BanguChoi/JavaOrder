package com.javaOrder.member.cart.service;

import java.util.List;

import com.javaOrder.member.cart.domain.CartItem;

public interface CartItemService {
	List<CartItem> cartItemList(CartItem cartItem);
	void insertCartItem(CartItem cartItem);
	void updateCartItem(CartItem cartItem);
	void deleteCartItem(CartItem cartItem);
	void insertCartItem(String cartId, CartItem cartItem);
}
