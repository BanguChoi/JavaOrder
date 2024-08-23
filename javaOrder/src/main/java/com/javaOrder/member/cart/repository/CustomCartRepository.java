package com.javaOrder.member.cart.repository;

import com.javaOrder.member.cart.domain.Cart;

public interface CustomCartRepository {
    public Cart saveWithFeneratedId(Cart cart);
}
