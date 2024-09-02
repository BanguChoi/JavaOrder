package com.javaOrder.member.cart.service;

import java.util.List;

import com.javaOrder.admin.member.domain.Member;
import com.javaOrder.member.cart.domain.CartItem;

public interface CartItemService {
	List<CartItem> cartItemList(Member member);
	void updateCartItem(String itemId, int itemNum);
	void deleteCartItem(String itemId);
	CartItem insertCartItem(String cartId, CartItem cartItem);
>>>>>>> origin/cart2

}
