package com.javaOrder.member.cart.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.service.CartItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart/*")
@RequiredArgsConstructor
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	/* 카트에 담긴 아이템 리스트 */
	@GetMapping("/cartItemList")
	public String cartItemList(CartItem cartItem, Model model) {
		List<CartItem> cartItemList = cartItemService.cartItemList(cartItem);
		model.addAttribute(cartItemList);
		return "javaOrder/cart/cartItemList";
	}
	
	/* 카트에 아이템 추가 */
	@GetMapping("/insertCartItem")
	public String insertCartItem(CartItem cartItem) {
		cartItemService.insertCartItem(cartItem);
		return "redirect:/cart/cartItemList";
	}
	
	/* 카트에 담긴 아이템 수량 변경 업데이트 */
	@PostMapping("/updateCartItem")
	public String updateCartItem(CartItem cartItem) {
		cartItemService.updateCartItem(cartItem);
		return "redirect:/cart/" + cartItem.getItemId();
	}
	
	/* 카트에 담긴 아이템 삭제 */
	@DeleteMapping("/{itemId}")
	public String deleteCartItem(@PathVariable String itemId, CartItem cartItem) {
		cartItem.setItemId(itemId);
		cartItemService.deleteCartItem(cartItem);
		return "redirect:/cart/cartItemList";
	}

	
	
	
	
	
	
	
}
