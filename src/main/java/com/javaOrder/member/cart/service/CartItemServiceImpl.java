package com.javaOrder.member.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.javaOrder.common.util.service.IdGenerationService;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.repository.CartItemRepository;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.domain.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	private final IdGenerationService idGenerationService;	
	private final CartRepository cartRepository;
	@PersistenceContext
    private EntityManager entityManager;
	
    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

	
	@Override
	public List<CartItem> cartItemList(Member member) {
	    Optional<Cart> optionalCart = cartRepository.findByMember(member);
	    if (optionalCart.isPresent()) {
	        Cart cart = optionalCart.get();

	        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

	        return cartItems;
	    }
	    return new ArrayList<>();
	}


	@Override
	public CartItem insertCartItem(String cartId, CartItem cartItem) {
		Cart cart = cartRepository.findById(cartId).orElseThrow();
		
		String cartItemId = idGenerationService.generateId(cart.getCartId(), "item_id_seq", 4);
		cartItem.setItemId(cartItemId);
		cartItem.setCart(cart);
		
		CartItem insertCartItem = cartItemRepository.save(cartItem);
		
		return insertCartItem;
	}

	@Override
	public void updateCartItem(String itemId, int itemNum) {
		CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow();

		cartItem.setItemNum(itemNum);	// 카트에서 수량변경만 가능
		cartItemRepository.save(cartItem);
	}

	@Transactional
	public void deleteCartItem(String itemId) {
	    Query query = entityManager.createNativeQuery("DELETE FROM cart_item WHERE item_id = :itemId");
	    query.setParameter("itemId", itemId);
	    int deletedCount = query.executeUpdate();
	    logger.info("Deleted {} rows for item ID: {}", deletedCount, itemId);
	}
}
