package com.javaOrder.admin.notices.service;

import java.util.List;
import java.util.Optional;

import com.javaOrder.admin.notices.domain.Notices;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;



public interface NoticesAdminService {
	List<Notices> noticesList(Notices notices);
	void noticesInsert(Notices notices);
	Notices noticesDetail(Notices notices);
	Notices getNotices(Long noticesNo);
	void noticesUpdate(Notices notices);
	void noticesDelete(Notices notices);
	PageResponseDTO<Notices> list(PageRequestDTO pageRequestDTO);
	
	// 최근 공지사항 불러오기
	Optional<Notices> getLatestNotice();	
}
