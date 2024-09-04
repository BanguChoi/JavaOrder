package com.javaOrder.common.kakaopay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test/pay")
	public String payTest() {
		return "/member/order/test/paytest";
	}
}
