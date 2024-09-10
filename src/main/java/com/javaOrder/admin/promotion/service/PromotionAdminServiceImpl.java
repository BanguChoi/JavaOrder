package com.javaOrder.admin.promotion.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.promotion.domain.Promotion;
import com.javaOrder.admin.promotion.repository.PromotionRepository;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionAdminServiceImpl implements PromotionAdminService {
	
	
	private final PromotionRepository promotionRepository;
	
	/*
	@Override
	public List<Promotion> promotionList(Promotion promotion) {
		List<Promotion> promotionsList = null;
		promotionsList = (List<Promotion>)promotionRepository.findAll();
		return promotionsList;
	}*/

	
	@Override
	public PageResponseDTO<Promotion> list(PageRequestDTO pageRequestDTO){
	Pageable pageable = PageRequest.of(
		pageRequestDTO.getPage() -1, //1페이지가 0 부터 시작
		pageRequestDTO.getSize(), Sort.by("promotionCode").descending());
		
		// 검색 조건 받기
		String searchType = pageRequestDTO.getSearchType();
		String keyword = pageRequestDTO.getKeyword();
		String status = pageRequestDTO.getStatus();
		
		LocalDate startDate = null;
		LocalDate endDate = null;
		if(pageRequestDTO.getStartDate() != null)
			startDate = pageRequestDTO.getStartDate();//.atStartOfDay();
		if(pageRequestDTO.getEndDate() != null)
			endDate = pageRequestDTO.getEndDate();//.atTime(23,59,59);
	
//		Page<Promotion> result = promotionRepository.findAll(pageable);
		Page<Promotion> result;
		
		if("memberCode".equals(searchType) && keyword != null) {
                result = promotionRepository.findByPromotionTitleContaining(keyword, pageable);
		}else if("orderStatus".equals(searchType) && status != null) {
			result = promotionRepository.findByPromotionStatus(status, pageable);
		}else if(startDate !=null && endDate != null) {
			result = promotionRepository.findByPromotionStartDateLessThanEqualAndPromotionEndDateGreaterThanEqual(startDate, endDate, pageable);
		}else{
			result = promotionRepository.findAll(pageable);
		}
		
		List<Promotion> promotionList = result.getContent().stream().collect(Collectors.toList());
		long totalCount = result.getTotalElements();
		PageResponseDTO<Promotion> responseDTO = PageResponseDTO.<Promotion>withAll()
											.dtoList(promotionList)
											.pageRequestDTO(pageRequestDTO)
											.totalCount(totalCount)
											.build();
		
		return responseDTO;
	}

	
	
	@Override
	public void promotionInsert(Promotion promotion) {
		promotionRepository.save(promotion);
	}

	// 프로모션 추가
	@Override
	public void createPromotion(Promotion promotion) {
		promotionRepository.save(promotion);
	}
	
	@Override
	public Promotion getPromotionByCode(Long promotionCode) {
		return promotionRepository.findById(promotionCode).orElseThrow(()->new IllegalArgumentException("Invalid product ID: "+promotionCode));
	}
	
	@Override
	public void savePromotion(Promotion updatePromotion) {
		promotionRepository.save(updatePromotion);
	}
	
	
	@Override
	public Promotion promotionDetail(Promotion promotion) {
		Optional<Promotion> promotionOptional = promotionRepository.findById(promotion.getPromotionCode());
		Promotion detail = promotionOptional.get();
		return detail;
	}

	@Override
	public Promotion getPromotion(Long promCode) {
		Optional<Promotion> promotionOptional = promotionRepository.findById(promCode);
		Promotion updateData = promotionOptional.orElseThrow();
		return updateData;
	}
	
	@Override
	public void promotionUpdate(Promotion promotion) {
		Optional<Promotion> promotionOptional = promotionRepository.findById(promotion.getPromotionCode());
		Promotion updatePromotion = promotionOptional.get();
		
		updatePromotion.setPromotionTitle(promotion.getPromotionTitle());
		updatePromotion.setPromotionStartDate(promotion.getPromotionStartDate());
		updatePromotion.setPromotionEndDate(promotion.getPromotionEndDate());
		updatePromotion.setPromotionStatus(promotion.getPromotionStatus());
		promotionRepository.save(updatePromotion);
//		promotionRepository.save(promotion);
	}
		
	// 프로모션 상태 수정
	@Override
	public void updatePromotion(Promotion promotion) {
		Optional<Promotion> promotionOptional = promotionRepository.findById(promotion.getPromotionCode());
		Promotion updatePromotion = promotionOptional.get();
		
		updatePromotion.setPromotionStatus(promotion.getPromotionStatus());
		promotionRepository.save(updatePromotion);
	}
	
	// 프로모션 삭제
	@Override
	public void promotionDelete(Promotion promotion) {
		promotionRepository.deleteById(promotion.getPromotionCode());
	}
	
	// 프로모션 이미지 메인에 출력
	@Override
	public List<Promotion> getOngoingPromotions() {
        return promotionRepository.findOngoingPromotions();
	}

}
