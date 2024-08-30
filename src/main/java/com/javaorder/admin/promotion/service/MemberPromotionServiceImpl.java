package com.javaorder.admin.promotion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaorder.admin.promotion.domain.Promotion;
import com.javaorder.admin.promotion.repository.PromotionRepository;

import lombok.Setter;

@Service
public class MemberPromotionServiceImpl implements MemberPromotionService {
	
	@Setter(onMethod_=@Autowired )
	private PromotionRepository repository;
	
	@Override
	public List<Promotion> promotionList(Promotion promotion) {
		List<Promotion> promotionsList = null;
		promotionsList = (List<Promotion>)repository.findAll();
		return promotionsList;
	}
//페이징 실패 마지막에 다시 시도
//	@Override
//	public PageResponseDTO<Promotion> listSer(PageRequestDTO pageRequestDTO) {
//		Pageable pageable = PageRequest.of(
//				pageRequestDTO.getPage() -1,	// 1페이지가 0이므로 주의
//				pageRequestDTO.getSize(), Sort.by("no").descending());
//		Page<Promotion> result = repository.findAll(pageable);
//		List<Promotion> promotionList = result.getContent().stream().collect(Collectors.toList());
//		
//		long totalCount = result.getTotalElements();
//		
//		PageResponseDTO<Promotion> responseDTO = PageResponseDTO.<Promotion>withAll().dtoList(promotionList)
//				.pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
//		
//		return responseDTO;
//	}
	
	@Override
	public Promotion promotionDetail(Promotion promotion) {
		Optional<Promotion> promotionOptional = repository.findById(promotion.getPromCode());
		Promotion detail = promotionOptional.get();
		return detail;
	}
}
