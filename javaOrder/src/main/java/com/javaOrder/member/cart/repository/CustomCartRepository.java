package com.javaOrder.member.cart.repository;

import com.javaOrder.member.cart.vo.Cart;

public interface CustomCartRepository {
    public Cart saveWithFeneratedId(Cart cart);
}
