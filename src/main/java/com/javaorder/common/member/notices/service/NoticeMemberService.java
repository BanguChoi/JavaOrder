package com.javaorder.common.member.notices.service;

import java.util.List;

import com.javaorder.common.admin.notices.domain.Notices;
import com.javaorder.common.util.vo.PageRequestDTO;
import com.javaorder.common.util.vo.PageResponseDTO;

public interface NoticeMemberService {
	List<Notices> noticesList(Notices notices);
	Notices noticesDetail(Notices notices);
	PageResponseDTO<Notices> list(PageRequestDTO pageRequestDTO);	

}
