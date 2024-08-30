package com.javaorder.notices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaorder.notices.domain.Notices;
import com.javaorder.notices.repository.NoticesRepository;

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
	public Notices noticesDetail(Notices notices) {
		Optional<Notices> optionalNotices = repository.findById(notices.getNoticesNo());
		Notices detail = optionalNotices.get();
		return detail;
	}
}
