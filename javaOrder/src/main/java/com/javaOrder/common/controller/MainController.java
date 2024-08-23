package com.javaOrder.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String memberMain() {
		return "member/main";
	}
	@GetMapping("/admin")
	public String adminMain() {
		return "admin/main";
	}
}