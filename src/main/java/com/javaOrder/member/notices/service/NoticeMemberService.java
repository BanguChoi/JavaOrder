package com.javaOrder.member.notices.service;

import java.util.List;

import com.javaOrder.admin.notices.domain.Notices;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

public interface NoticeMemberService {
	List<Notices> noticesList(Notices notices);
	Notices noticesDetail(Notices notices);
	PageResponseDTO<Notices> list(PageRequestDTO pageRequestDTO);	

}
