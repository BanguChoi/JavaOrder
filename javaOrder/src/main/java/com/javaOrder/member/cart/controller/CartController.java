package com.javaOrder.member.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.cart.service.CartService;
import com.javaOrder.member.cart.vo.Cart;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Controller
@RequestMapping("/javaOrder/*")
@RequiredArgsConstructor
public class CartController {

	@Setter(onMethod_ = @Autowired)
	private CartService cartService;

	@GetMapping("/cartList")
	public String cartList(Cart cart, Model model) {
		List<Cart> cartList = cartService.cartList(cart);
		model.addAttribute("cartList", cartList);
		return "client/javaOrder/cartList";
	}
	
	@GetMapping("/updateCart")
	public String updateCart(Cart cart) {
		cartService.updateCart(cart);
		return "redirect:/javaOrder/cartList";
	}
	
	@DeleteMapping("/{cartId}")
	public String deleteCart(Cart cart) {
		cartService.deleteCart(cart);
		return "redirect:/javaOrder/cartList";
	}

}
