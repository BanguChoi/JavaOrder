package com.javaorder.notices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaorder.notices.domain.Notices;
import com.javaorder.notices.service.NoticesService;

import lombok.Setter;

@Controller
@RequestMapping("/memberNotices")
public class NoticesMemberController {

	@Setter(onMethod_=@Autowired)
	private NoticesService service;

	@GetMapping("/noticesList")
	public String noticesList(Notices notices,Model model) {
		List<Notices> noticesList = service.noticesList(notices);
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
		
	
}
