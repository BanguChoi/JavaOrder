package com.javaOrder.admin.promotion.service;

import java.util.List;

import com.javaOrder.admin.promotion.domain.Promotion;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

public interface PromotionAdminService {
//	List<Promotion> promotionList(Promotion promotion);
	// 검색 및 페이징 추가 리스트 조회
	PageResponseDTO<Promotion> list(PageRequestDTO pageRequestDTO);
	
	void promotionInsert(Promotion promotion);
	Promotion promotionDetail(Promotion promotion);
	Promotion getPromotion(Long promCode);
	void promotionUpdate(Promotion promotion);
	void promotionDelete(Promotion promotion);
	
	// 프로모션 생성
	void createPromotion(Promotion promotion);
	Promotion getPromotionByCode(Long promotionCode);
	void savePromotion(Promotion updatePromotion);
	// 프로모션 상태 수정
	void updatePromotion(Promotion promotion);
	// 프로모션 이미지 메인에 출력
	List<Promotion> getOngoingPromotions();
	

}
