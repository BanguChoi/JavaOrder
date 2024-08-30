package com.javaorder.admin.promotion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaorder.admin.promotion.domain.Promotion;
import com.javaorder.admin.promotion.service.PromotionService;

import lombok.Setter;

@Controller
@RequestMapping("/memberPromotion/*")
public class promotionMemberController {

	
	@Setter(onMethod_=@Autowired)
	private PromotionService service;
	

	@GetMapping("/promotionList")
	public String promotionList(Promotion promotion,Model model) {
		List<Promotion> promotionList = service.promotionList(promotion);
		model.addAttribute("promotionList", promotionList);		
		return "member/promotion/promotionList";
	}
//페이징 처리를 위해서 밑에거 나중에 나중에
//	@GetMapping("/promotionList")
//	public String promotionList(Promotion promotion, PageRequestDTO pageRequestDTO, Model model) {
//		PageResponseDTO<Promotion> promotionList = service.list(pageRequestDTO);
//		model.addAttribute("promotionList", promotionList);
//		return "promotion/admin/promotionList";
//	}
	
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
