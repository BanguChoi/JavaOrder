package com.javaOrder.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
	
	@GetMapping("/javaOrder/admin")
	public String adminMain(Model model) {
		return "/admin/dashBoard/dashBoard";
	}
}
