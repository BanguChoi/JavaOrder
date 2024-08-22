package com.javaOrder.member.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("javaOrder/main")
	public String main() {
		return "javaOrder/main";
	}

}
