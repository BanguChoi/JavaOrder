package com.javaorder.admin.promotion.service;

import java.util.List;

import com.javaorder.admin.promotion.domain.Promotion;

public interface PromotionService {
	List<Promotion> promotionList(Promotion promotion);
	void promotionInsert(Promotion promotion);
	Promotion promotionDetail(Promotion promotion);
	Promotion getPromotion(Long promCode);
	void promotionUpdate(Promotion promotion);
	void promotionDelete(Promotion promotion);
	//PageResponseDTO<Promotion> listSer(PageRequestDTO pageRequestDTO); 실패 페이징은 맨 마지막에

}
