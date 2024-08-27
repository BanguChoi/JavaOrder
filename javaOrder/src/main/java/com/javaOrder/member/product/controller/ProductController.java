package com.javaOrder.member.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.cart.service.CartItemService;
import com.javaOrder.member.product.domain.Product;
import com.javaOrder.member.product.service.ProductService;

import lombok.Setter;


@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;
	
	@Setter(onMethod_ = @Autowired)
	private CartItemService cartItemService;

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


}
