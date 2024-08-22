package com.javaOrder.member.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.cart.vo.Cart;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;

	@Override
	public List<Cart> cartList(Cart cart) {
		List<Cart> cartList = cartRepository.findAll();
		return cartList;
	}

	/*
	@Override
	public void insertCart(Cart cart) {
		cartRepository.save(cart);
	}
	*/

	@Override
	public void updateCart(Cart cart) {
		Optional<Cart> cartOptional = cartRepository.findById(cart.getCartId());
		Cart updateCart = cartOptional.get();

		updateCart.setCartPrice(cart.getCartPrice());
		cartRepository.save(updateCart);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepository.deleteById(cart.getCartId());
	}



}
