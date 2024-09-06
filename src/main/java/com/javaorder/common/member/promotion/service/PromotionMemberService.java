package com.javaorder.common.member.promotion.service;

import java.util.List;

import com.javaorder.common.admin.promotion.domain.Promotion;
import com.javaorder.common.util.vo.PageRequestDTO;
import com.javaorder.common.util.vo.PageResponseDTO;

public interface PromotionMemberService {
	List<Promotion> promotionList(Promotion promotion);	
	Promotion promotionDetail(Promotion promotion);
	PageResponseDTO<Promotion> list(PageRequestDTO pageRequestDTO);

}
