package com.javaOrder.member.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.cart.vo.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, String>{

}
