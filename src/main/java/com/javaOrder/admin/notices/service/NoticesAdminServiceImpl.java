package com.javaOrder.admin.notices.service;

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
public class NoticesAdminServiceImpl implements NoticesAdminService {

	@Setter(onMethod_=@Autowired )
	private NoticesRepository repository;
	

	@Override
	public List<Notices> noticesList(Notices notices) {
		List<Notices> noticesList = null;
		noticesList = repository.findAll();
		return noticesList;
	}
//페이징 실패 마지막에 다시 시도
	@Override
	public PageResponseDTO<Notices> list(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage() -1,	// 1페이지가 0이므로 주의
				pageRequestDTO.getSize(), Sort.by("noticesNo").descending());
		Page<Notices> result = repository.findAll(pageable);
		List<Notices> noticesList = result.getContent().stream().collect(Collectors.toList());
		
		long totalCount = result.getTotalElements();
		
		PageResponseDTO<Notices> responseDTO = PageResponseDTO.<Notices>withAll().dtoList(noticesList)
				.pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
		
		return responseDTO;
	}
	
	@Override
	public void noticesInsert(Notices notices) {
		repository.save(notices);
	}


	@Override
	public Notices noticesDetail(Notices notices) {
		Optional<Notices> noticesOptional = repository.findById(notices.getNoticesNo());
		Notices detail = noticesOptional.get();
		return detail;
	}


	@Override
	public void noticesUpdate(Notices notices) {
		Optional<Notices> noticesOptional = repository.findById(notices.getNoticesNo());
		Notices updateNotices = noticesOptional.get();
		
		updateNotices.setNoticesTitle(notices.getNoticesTitle());
		updateNotices.setNoticesContent(notices.getNoticesContent());
		repository.save(updateNotices);
	}

	@Override
	public void noticesDelete(Notices notices) {
		repository.deleteById(notices.getNoticesNo());

	}

	@Override
	public Notices getNotices(Long noticesNo) {
		Optional<Notices> noticesOptional = repository.findById(noticesNo);
		Notices updateData = noticesOptional.orElseThrow();
		return updateData;
	}
	
	@Override
	public Optional<Notices> getLatestNotice() {
		return repository.findFirstByOrderByRegDateDesc();
	}
}