package com.javaOrder.member.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.service.CartItemService;
import com.javaOrder.member.cart.service.CartService;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.product.domain.Product;
import com.javaOrder.member.product.repository.ProductRepository;
import com.javaOrder.member.product.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;


@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;
	
	@Setter(onMethod_ = @Autowired)
	private CartService cartService;
	
	@Setter(onMethod_ = @Autowired)
	private CartItemService cartItemService;
	
	@Setter(onMethod_ = @Autowired)
	private ProductRepository productRepository;

	@GetMapping("/productList")
	public String productList(Product product, Model model) {
		List<Product> productList = productService.productList(product);
		model.addAttribute("productList", productList);
		return "/member/product/productList";
	}
	


	@GetMapping("/{productId}")
	public String productDetail(@PathVariable String productId, Model model, HttpSession session)  {
		Product productDetail = productService.getProductById(productId);	
		
		Member member = (Member) session.getAttribute("member");
		if(member != null) {
			Cart cart = cartService.getCartByMemberCode(member.getMemberCode());
			model.addAttribute("cart", cart);
		}

		model.addAttribute("productDetail", productDetail);
		return "/member/product/productDetail";
	}
	
	/* 제품 가격 데이터만 전송. 상세페이지 ajax 용 */
	@GetMapping("/totalPrice")
	@ResponseBody
	public int totalPrice(@RequestParam String productId) {
		Product product = productRepository.findById(productId).orElseThrow();
		return product.getProductPrice();
	}
	

}
