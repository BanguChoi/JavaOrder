package com.javaorder.common.admin.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaorder.common.admin.promotion.domain.Promotion;
import com.javaorder.common.admin.promotion.service.PromotionAdminService;
import com.javaorder.common.util.vo.PageRequestDTO;
import com.javaorder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Controller
@RequestMapping("/adminPromotion/*")
public class PromotionAdminController {
	
	@Setter(onMethod_=@Autowired)
	private PromotionAdminService service;
	
	@GetMapping("/")
	public String main() {
		return "/main";
	}

	@GetMapping("/promotionList")
	public String promotionList(Promotion promotion, PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<Promotion> promotionList = service.list(pageRequestDTO);
		model.addAttribute("promotionList", promotionList);
		return "admin/promotion/promotionList";
	}
	
	@GetMapping("/insertForm")
	public String insertForm(Promotion promotion) {
		return "admin/promotion/insertForm";
	}
	@PostMapping("/promotionInsert")
	public String promotionInsert(Promotion promotion) {
		service.promotionInsert(promotion);
		return "redirect:/adminPromotion/promotionList";
	}
	
	@GetMapping("/{promCode}")
	public String promotionDetail(@PathVariable Long promCode, Promotion promotion, Model model) {
		promotion.setPromCode(promCode);
		Promotion detail = service.promotionDetail(promotion);
		model.addAttribute("detail",detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		return "admin/promotion/promotionDetail";
	}
		
	@PostMapping("/updateForm")
	public String updateForm(Promotion promotion, Model model) {
		Promotion updateData = service.getPromotion(promotion.getPromCode());
		model.addAttribute("updateData", updateData);
		return "admin/promotion/updateForm";
	}
	
	@PostMapping("/promotionUpdate")
	public String promotionUpdate(Promotion promotion) {
		service.promotionUpdate(promotion);
		return "redirect:/adminPromotion/"+promotion.getPromCode();//이동이 안될시 확인 확인 성공
	}
	
	@PostMapping("/promotionDelete")
	public String promotionDelete(Promotion promotion) {
		service.promotionDelete(promotion);
		return "redirect:/adminPromotion/promotionList"; //이동이 안될시 확인 성공
	}
	
	
}
