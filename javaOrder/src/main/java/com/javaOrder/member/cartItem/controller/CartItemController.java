package com.javaOrder.member.cartItem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.cartItem.service.CartItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/javaOrder/*")
@RequiredArgsConstructor
public class CartItemController {

	private final CartItemService cartItemService;




}
