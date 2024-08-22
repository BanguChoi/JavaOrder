package com.javaOrder.member.cartItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.cartItem.vo.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, String>{

}
