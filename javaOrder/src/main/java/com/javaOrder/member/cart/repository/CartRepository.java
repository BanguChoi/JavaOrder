package com.javaOrder.member.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.cart.vo.Cart;

public interface CartRepository extends JpaRepository<Cart, String>{

}
