package com.javaOrder.member.cart.service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.main.controller.MainController;
import com.javaOrder.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;

	private final MemberRepository memberRepository;
	
	/*
	@Override
	public Optional<Cart> cartList(Member member) {
		Optional<Cart> cartList = cartRepository.findByMember(member);
		return cartList;
	}
	*/
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	
	/* 로그인한 멤버 카트 찾기 */
	@Override
	public Cart getCartByMemberCode(String memberCode) {
		Cart cart = cartRepository.findByMember_MemberCode(memberCode);
		if(cart == null) {
			logger.warn("No cart found for member code: {}", memberCode);
			cart = new Cart();
			cart.setCartId("C" + memberCode);
		} else {
			logger.info("Found cart with ID: {}", cart.getCartId());
	    }
	    if (cart.getCartItems() == null) {
	        cart.setCartItems(new ArrayList<>());
		}
	    logger.info("Cart: {}", cart);
		return cart;
	}
	

	/* 카트 생성 */
	@Transactional
	@Override
	public Cart createCart(String memberCode) {
        Cart findCart = cartRepository.findByMember_MemberCode(memberCode);
        
        if (findCart == null) {
        	 Cart newCart = new Cart();
             newCart.setCartId("C" + memberCode);
        }
        return findCart;
    
	}
	

	/* 카트 업데이트 */
	@Override
	public void updateCart(int cartPrice, String memberCode) {
		Member member = memberRepository.findCartByMemberCode(memberCode);
		
		if(member == null) {
			throw new NoSuchElementException("회원정보를 조회할 수 없음");
		}
		Optional<Cart> cartOptonal = cartRepository.findByMember(member);
		
		if(cartOptonal.isPresent()) {
			Cart cart = cartOptonal.get();
			cart.setCartPrice(cartPrice);
			cartRepository.save(cart);
		} else {
			throw new NoSuchElementException(memberCode + " 회원의 카트를 찾을 수 없음");
		}
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepository.deleteById(cart.getCartId());
	}

}
