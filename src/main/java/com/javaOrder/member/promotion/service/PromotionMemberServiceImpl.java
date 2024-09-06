package com.javaOrder.member.promotion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.promotion.domain.Promotion;
import com.javaOrder.admin.promotion.repository.PromotionRepository;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Service
public class PromotionMemberServiceImpl implements PromotionMemberService {
	
	@Setter(onMethod_=@Autowired )
	private PromotionRepository repository;
	
	@Override
	public List<Promotion> promotionList(Promotion promotion) {
		List<Promotion> promotionsList = null;
		promotionsList = (List<Promotion>)repository.findAll();
		return promotionsList;
	}
//@Override
	public PageResponseDTO<Promotion> list(PageRequestDTO pageRequestDTO){
	Pageable pageable = PageRequest.of(
		pageRequestDTO.getPage() -1, //1페이지가 0 부터 시작
		pageRequestDTO.getSize(), Sort.by("promCode").descending());
		
		Page<Promotion> result = repository.findAll(pageable);
		List<Promotion> promotionList = result.getContent().stream().collect(Collectors.toList());
		long totalCount = result.getTotalElements();
		PageResponseDTO<Promotion> responseDTO = PageResponseDTO.<Promotion>withAll().dtoList(promotionList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
		
		return responseDTO;
	}
	
	@Override
	public Promotion promotionDetail(Promotion promotion) {
		Optional<Promotion> promotionOptional = repository.findById(promotion.getPromCode());
		Promotion detail = promotionOptional.get();
		return detail;
	}
}
