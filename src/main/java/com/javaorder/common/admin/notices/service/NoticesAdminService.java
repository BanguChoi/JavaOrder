package com.javaorder.common.admin.notices.service;

import java.util.List;

import com.javaorder.common.admin.notices.domain.Notices;
import com.javaorder.common.util.vo.PageRequestDTO;
import com.javaorder.common.util.vo.PageResponseDTO;



public interface NoticesAdminService {
	List<Notices> noticesList(Notices notices);
	void noticesInsert(Notices notices);
	Notices noticesDetail(Notices notices);
	Notices getNotices(Long noticesNo);
	void noticesUpdate(Notices notices);
	void noticesDelete(Notices notices);
	PageResponseDTO<Notices> list(PageRequestDTO pageRequestDTO);	

}
