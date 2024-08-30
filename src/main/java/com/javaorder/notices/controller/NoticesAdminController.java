package com.javaorder.notices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaorder.notices.domain.Notices;
import com.javaorder.notices.service.NoticesService;

import lombok.Setter;


@Controller
@RequestMapping("/adminNotices/*")
public class NoticesAdminController {

		@Setter(onMethod_=@Autowired)
		private NoticesService service;
	
		@GetMapping("/noticesList")
		public String noticesList(Notices notices,Model model) {
			List<Notices> noticesList = service.noticesList(notices);
			model.addAttribute("noticesList", noticesList);		
			return "admin/notices/noticesList";
		}
		
		@GetMapping("/insertForm")
		public String insertForm(Notices notices) {
			return "admin/notices/insertForm";
		}
		@PostMapping("/noticesInsert")
		public String noticesInsert(Notices notices) {
			service.noticesInsert(notices);
			return "redirect:/adminNotices/noticesList";
		}
		
		@GetMapping("/{noticesNo}")
		public String noticesDetail(@PathVariable Long noticesNo, Notices notices, Model model) {
			notices.setNoticesNo(noticesNo);
			Notices detail = service.noticesDetail(notices);
			model.addAttribute("detail",detail);
			
			String newLine = System.getProperty("line.separator").toString();
			model.addAttribute("newLine", newLine);
			return "admin/notices/noticesDetail";
		}
			
		@PostMapping("/updateForm")
		public String updateForm(Notices notices, Model model) {
			Notices updateData = service.getNotices(notices.getNoticesNo());
			model.addAttribute("updateData", updateData);
			return "admin/notices/updateForm";
		}
		
		@PostMapping("/noticesUpdate")
		public String noticesUpdate(Notices notices) {
			service.noticesUpdate(notices);
			return "redirect:/adminNotices/"+notices.getNoticesNo();//이동이 안될시 확인
		}
		
		
		@PostMapping("/noticesDelete")
		public String noticesDelete(Notices notices) {
			service.noticesDelete(notices);
			return "redirect:/adminNotices/noticesList"; //이동이 안될시 확인 성공
		}
		
}
