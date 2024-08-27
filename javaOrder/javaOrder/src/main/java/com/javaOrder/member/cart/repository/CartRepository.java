package com.javaOrder.member.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.domain.Member;

public interface CartRepository extends JpaRepository<Cart, String>{
	Cart findBymemberCode(Member mCode);
}