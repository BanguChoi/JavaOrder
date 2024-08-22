package com.javaOrder.cart.repository;

import com.javaOrder.cart.domain.Cart;

public interface CustomCartRepository {
	public Cart saveWithFeneratedId(Cart cart);
}
