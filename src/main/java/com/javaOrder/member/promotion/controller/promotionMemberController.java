package com.javaOrder.member.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.admin.promotion.domain.Promotion;
import com.javaOrder.admin.promotion.service.PromotionAdminService;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Controller
@RequestMapping("/memberPromotion/*")
public class promotionMemberController {

	
	@Setter(onMethod_=@Autowired)
	private PromotionAdminService service;
	

	
	@GetMapping("/promotionList")
	public String promotionList(Promotion promotion, PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<Promotion> promotionList = service.list(pageRequestDTO);
		model.addAttribute("promotionList", promotionList);
		return "member/promotion/promotionList";
	}
	
	@GetMapping("/{promCode}")
	public String promotionDetail(@PathVariable Long promCode, Promotion promotion, Model model) {
		promotion.setPromCode(promCode);
		Promotion detail = service.promotionDetail(promotion);
		model.addAttribute("detail",detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		return "member/promotion/promotionDetail";
	}
}