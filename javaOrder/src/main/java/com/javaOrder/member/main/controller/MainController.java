package com.javaOrder.member.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaOrder.member.product.domain.Product;
import com.javaOrder.member.product.service.ProductService;

import lombok.Setter;

@Controller
public class MainController {
	
	/* 메인 화면에 productList, cart, cartList를 볼 수 있도록 해야 해서 service 추가 */
	
	@Setter(onMethod_ = @Autowired)
	private ProductService productService;
	
	/*
	@Setter(onMethod_ = @Autowired)
	private CartService cartService;
	*/
	
	/*
	@Setter(onMethod_ = @Autowired)
	private CartItemService cartItemService;
	*/
	
	@GetMapping("/member/main")
	public String main(Model model) {
		
		List<Product> productList = productService.productList(new Product());
		model.addAttribute("productList", productList);
		
		/*
		List<Cart> cartList = cartService.cartList(new Cart());
		model.addAttribute("cartList", cartList);
		*/
		
		/*
		List<CartItem> cartItemList = cartItemService.cartItemList(new CartItem());
		model.addAttribute("cartItemList", cartItemList);
		*/
		
		return "javaOrder/member/main";
	}

}
