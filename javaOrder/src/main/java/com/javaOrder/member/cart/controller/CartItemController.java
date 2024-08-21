package com.javaOrder.member.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.cart.service.CartItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/javaOrder/*")
@RequiredArgsConstructor
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	
	
	
}
