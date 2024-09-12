package com.javaOrder.member.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.domain.Member;

public interface CartRepository extends JpaRepository<Cart, String>{
	Optional<Cart> findCartByMember(Member member);
	Optional<Cart> findByMember(Member member);
	Cart findByMember_MemberCode(String memberCode);
}
