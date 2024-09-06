package com.javaorder.common.admin.promotion.service;

import java.util.List;

import com.javaorder.common.admin.promotion.domain.Promotion;
import com.javaorder.common.util.vo.PageRequestDTO;
import com.javaorder.common.util.vo.PageResponseDTO;

public interface PromotionAdminService {
	List<Promotion> promotionList(Promotion promotion);
	PageResponseDTO<Promotion> list(PageRequestDTO pageRequestDTO);
	void promotionInsert(Promotion promotion);
	Promotion promotionDetail(Promotion promotion);
	Promotion getPromotion(Long promCode);
	void promotionUpdate(Promotion promotion);
	void promotionDelete(Promotion promotion);
	

}
