package com.javaOrder.member.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaOrder.common.service.IdGenerationService;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.repository.CartItemRepository;
import com.javaOrder.member.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	
	@Autowired
	private IdGenerationService idGenerationService;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	@Override
	public List<CartItem> cartItemList(CartItem cartItem) {
		List<CartItem> cartItemList = cartItemRepository.findAll();
		return cartItemList;
	}
	
	
	@Override
	public void insertCartItem(String cartId, CartItem cartItem) {
		Optional<Cart> cartOptional = cartRepository.findById(cartId);
		
		if(cartOptional.isPresent()) {
			Cart cart = cartOptional.get();
			
			String cartItemId = idGenerationService.generateId(cart.getCartId(), "item_id_seq");
			cartItem.setItemId(cartItemId);
			
			cartItemRepository.save(cartItem);
		}
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


	@Override
	public void insertCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		
	}





}
