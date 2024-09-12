package com.javaOrder.member.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.service.CartService;
import com.javaOrder.member.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Controller
@RequestMapping("/cart/*")
@RequiredArgsConstructor
public class CartController {

	@Setter(onMethod_ = @Autowired)
	private CartService cartService;

	@GetMapping("/cartList")
	public String cartList(HttpSession session, Model model) {
	    // 세션에서 로그인된 사용자 정보 가져오기
	    Member member = (Member) session.getAttribute("member");
	    
	    Cart cart = cartService.getCartByMemberCode(member.getMemberCode());
	    model.addAttribute("cart", cart);
	    model.addAttribute("member", member);
	    return "/member/cart/cartList";
	}


	@PostMapping("/createCart")
	@ResponseBody
	public String createCart(@RequestParam String memberCode) {
		cartService.createCart(memberCode);
		return "redirect:/member/cart/cartList";
	}

	@PostMapping("/updateCart")
	public String updateCart(@RequestBody int cartPrice, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		if(member != null) {
			String memberCode = member.getMemberCode();
			cartService.updateCart(cartPrice, memberCode);
		}
		return "redirect:/";
	}

	@DeleteMapping("/deleteCart/{cartId}")
	public String deleteCart(@PathVariable String cartId) {
	    Cart cart = new Cart();
	    cart.setCartId(cartId);
		cartService.deleteCart(cart);
		return "redirect:/member/cart/cartList";
	}
	
}
