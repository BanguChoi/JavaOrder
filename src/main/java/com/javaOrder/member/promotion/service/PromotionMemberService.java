package com.javaOrder.member.promotion.service;

import com.javaOrder.admin.promotion.domain.Promotion;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

public interface PromotionMemberService {
//	List<Promotion> promotionList(Promotion promotion);	
	Promotion promotionDetail(Promotion promotion);
	PageResponseDTO<Promotion> list(PageRequestDTO pageRequestDTO);

}
