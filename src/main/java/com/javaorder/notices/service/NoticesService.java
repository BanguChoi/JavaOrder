package com.javaorder.notices.service;

import java.util.List;

import com.javaorder.notices.domain.Notices;



public interface NoticesService {
	List<Notices> noticesList(Notices notices);
	void noticesInsert(Notices notices);
	Notices noticesDetail(Notices notices);
	Notices getNotices(Long noticesNo);
	void noticesUpdate(Notices notices);
	void noticesDelete(Notices notices);
	//PageResponseDTO<Notices> listSer(PageRequestDTO pageRequestDTO); 실패 페이징은 맨 마지막에

}
