package com.javaOrder.member.cartItem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaOrder.member.cartItem.repository.CartItemRepository;
import com.javaOrder.member.cartItem.vo.CartItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;

	@Override
	public List<CartItem> cartItemList(CartItem cartItem) {
		List<CartItem> cartItemList = cartItemRepository.findAll();
		return cartItemList;
	}

	@Override
	public void insertCartItem(CartItem cartItem) {
		cartItemRepository.save(cartItem);
	}

	@Override
	public void updateCartItem(CartItem cartItem) {
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItem.getItemId());
		CartItem updateCartItem = cartItemOptional.get();

		updateCartItem.setItemNum(cartItem.getItemNum());	// 카트에서 수량변경만 가능
		cartItemRepository.save(updateCartItem);
	}

	@Override
	public void deleteCartItem(CartItem cartItem) {
		cartItemRepository.deleteById(cartItem.getItemId());
	}

}
