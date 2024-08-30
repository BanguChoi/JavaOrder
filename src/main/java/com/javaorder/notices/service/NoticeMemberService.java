package com.javaorder.notices.service;

import java.util.List;

import com.javaorder.notices.domain.Notices;

public interface NoticeMemberService {
	List<Notices> noticesList(Notices notices);
	Notices noticesDetail(Notices notices);
	
}
