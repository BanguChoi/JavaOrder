package com.javaOrder.member.notices.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.notices.domain.Notices;
import com.javaOrder.admin.notices.repository.NoticesRepository;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Service
public class NoticeMemberServiceImpl implements NoticeMemberService {
	
	@Setter(onMethod_=@Autowired)
	private NoticesRepository repository;
	
	@Override
	public List<Notices> noticesList(Notices notices) {
		List<Notices> noticesList = null;
		noticesList = repository.findAll();
		return noticesList;
	}
	
	@Override
	public PageResponseDTO<Notices> list(PageRequestDTO pageRequestDTO){
	Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage() -1, //1페이지가 0 부터 시작
				pageRequestDTO.getSize(), Sort.by("noticesNo").descending());//이 no가 NoticesNo 인지 주의 할것
		
		Page<Notices> result = repository.findAll(pageable);
		List<Notices> noticesList = result.getContent().stream().collect(Collectors.toList());
		long totalCount = result.getTotalElements();
		PageResponseDTO<Notices> responseDTO = PageResponseDTO.<Notices>withAll().dtoList(noticesList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
		
		return responseDTO;
	}

	@Override
	public Notices noticesDetail(Notices notices) {
		Optional<Notices> optionalNotices = repository.findById(notices.getNoticesNo());
		Notices detail = optionalNotices.get();
		return detail;
	}
}
