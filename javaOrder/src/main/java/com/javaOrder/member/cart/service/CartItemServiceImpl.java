package com.javaOrder.member.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.repository.CartItemRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<CartItem> cartItemList(CartItem cartItem) {
		List<CartItem> cartItemList = cartItemRepository.findAll();
		return cartItemList;
	}
	
	@Transactional
	@Override
	public void insertCartItem(CartItem cartItem) {
		long itemIdSeqence = getItemIdSeqenceValue();
		cartItem.setItemIdSeq(itemIdSeqence);
		cartItemRepository.save(cartItem);
	}
	/* 카트 아이템 시퀀스를 위한 쿼리문 */
	public Long getItemIdSeqenceValue() {
		Query query = entityManager.createNativeQuery("SELECT item_id_seq.NEXTVAL FROM dual");
		return ((Number) query.getSingleResult()).longValue();
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
