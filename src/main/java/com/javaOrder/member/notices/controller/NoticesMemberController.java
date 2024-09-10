package com.javaOrder.member.notices.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.admin.notices.domain.Notices;
import com.javaOrder.admin.notices.service.NoticesAdminService;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Controller
@RequestMapping("/member/notices/*")
public class NoticesMemberController {

	@Setter(onMethod_=@Autowired)
	private NoticesAdminService service;

	@GetMapping("/noticesList")
	public String noticesList(Notices notices, PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<Notices> noticesList = service.list(pageRequestDTO);
		model.addAttribute("noticesList", noticesList);
		
		return "member/notices/noticesList";
	}
	
	
	
	@GetMapping("/{noticesNo}")
	public String noticesDetail(@PathVariable Long noticesNo, Notices notices, Model model) {
		notices.setNoticesNo(noticesNo);
		Notices detail = service.noticesDetail(notices);
		model.addAttribute("detail",detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		return "member/notices/noticesDetail";
	}
	
	@GetMapping("/latest")
	public ResponseEntity<Notices> getLatestNotice(){
		Optional<Notices> notice = service.getLatestNotice();
		
		return ResponseEntity.ok(notice.get());
	}
}
