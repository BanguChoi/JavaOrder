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

import com.javaOrder.member.cart.service.CartItemService;
import com.javaOrder.member.product.domain.Product;
import com.javaOrder.member.product.repository.ProductRepository;
import com.javaOrder.member.product.service.ProductService;

import lombok.Setter;


@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;
	
	@Setter(onMethod_ = @Autowired)
	private CartItemService cartItemService;
	
	@Setter(onMethod_ = @Autowired)
	private ProductRepository productRepository;

	@GetMapping("/productList")
	public String productList(Product product, Model model) {
		List<Product> productList = productService.productList(product);
		model.addAttribute("productList", productList);
		return "javaOrder/member/product/productList";
	}


	@GetMapping("/{productId}")
	public String productDetail(@PathVariable String productId, Product product, Model model) {
		product.setProductId(productId);
		Product productDetail = productService.productDetail(product);	

		model.addAttribute("productDetail", productDetail);
		return "javaOrder/member/product/productDetail";
	}
	
	/* 제품 가격 데이터만 전송. 상세페이지 ajax 용 */
	@GetMapping("/totalPrice")
	@ResponseBody
	public int totalPrice(@RequestParam String productId) {
		Product product = productRepository.findById(productId).orElseThrow();
		return product.getProductPrice();
	}
	

}
